# 필요 라이브러리
import os
import random
import cv2
from matplotlib import pyplot as plt
from PIL import Image
import torchvision.transforms as transforms
import torchvision
import torch
from torchvision.datasets import ImageFolder
from torch.utils.data.dataloader import DataLoader 
import torch.nn as nn
from torch import optim
from torch.autograd import Variable


# 데이터 불러오기
current_directory = os.path.dirname(os.path.realpath(__file__))
data_directory = os.path.join(current_directory, 'GAN/data')
data_images_filepaths = sorted([os.path.join(data_directory, f) for f in os.listdir(data_directory)])
images_filepaths = [*data_images_filepaths]

gan_images_filepaths = [i for i in images_filepaths if cv2.imread(i) is not None]

random.seed(42)
random.shuffle(gan_images_filepaths)
#print(len(gan_images_filepaths))

# 시각화
# for i in range(5):
#     image = cv2.imread(gan_images_filepaths[i])
#     image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    
#     plt.imshow(image_rgb)
#     plt.axis('off')
#     plt.show()

# 데이터 전처리부터 로드까지
trans = transforms.Compose([transforms.Resize(190),
                           transforms.ToTensor(),
                           transforms.Normalize([0.5],[0.5])
                           ])

gan_directory = os.path.join(current_directory, 'GAN')
dataset = ImageFolder(
    root = gan_directory,
    transform=trans
)

train_data_loader = torch.utils.data.DataLoader(dataset, batch_size=32,
                                          num_workers=0, shuffle=True)

# 모델

latent_dim = 100
img_shape = (3,190,190)

class Generator(nn.Module):
    def __init__(self, latent_dim, img_shape):
        super(Generator, self).__init__()
        self.latent_dim = latent_dim
        self.img_shape = img_shape
        
        # 입력 크기 조정
        self.fc = nn.Linear(latent_dim, 128)
        self.model = nn.Sequential(
            nn.Linear(128, 256),
            nn.ReLU(inplace=True),
            nn.Linear(256, 512),
            nn.ReLU(inplace=True),
            nn.Linear(512, img_shape[0] * img_shape[1] * img_shape[2]),
            nn.Tanh()  # 생성된 이미지의 픽셀 값 범위를 (-1, 1)로 조정
        )

    def forward(self, z):
        x = self.fc(z)
        img = self.model(x)
        img = img.view(img.size(0), *self.img_shape)
        return img
    
class Discriminator(nn.Module):
    def __init__(self, img_shape):
        super(Discriminator, self).__init__()
        self.img_shape = img_shape
        
        # 입력 크기 조정
        self.fc = nn.Linear(img_shape[0] * img_shape[1] * img_shape[2], 512)
        self.model = nn.Sequential(
            nn.Linear(512, 256),
            nn.ReLU(inplace=True),
            nn.Linear(256, 1),
            nn.Sigmoid()  # 이진 분류를 위해 시그모이드 활성화 함수 사용
        )

    def forward(self, img):
        x = img.view(img.size(0), -1)
        x = self.fc(x)
        validity = self.model(x)
        return validity
    
from torch import optim

generator = Generator(latent_dim, img_shape)
discriminator = Discriminator(img_shape)

learning_rate = 0.0002
beta1 = 0.9

adversarial_loss = nn.BCELoss()

optimizer_G = optim.Adam(generator.parameters(), lr=learning_rate, betas=(beta1, 0.999))
optimizer_D = optim.Adam(discriminator.parameters(), lr=learning_rate, betas=(beta1, 0.999))

num_epochs = 500
interval_save_img = 1000

is_cuda = torch.cuda.is_available()
device = torch.device('cuda' if is_cuda else 'cpu')

Tensor = torch.cuda.FloatTensor if is_cuda else torch.FloatTensor

def random_sample_z_space(batch_size=1, dim_noise=100):
    return torch.randn(batch_size, dim_noise, device=device)

from torch.autograd import Variable

losses = []

for idx_epoch in range(num_epochs):
    for idx_batch, (imgs, _) in enumerate(train_data_loader):
        # Ground truth variables indicating real/fake
        real_ground_truth = Variable(Tensor(imgs.size(0), 1).fill_(1.0), requires_grad=False)
        fake_ground_truth = Variable(Tensor(imgs.size(0), 1).fill_(0.0), requires_grad=False)

        # Real image
        real_imgs = Variable(imgs.type(Tensor))

        #####################
        # Train Generator

        optimizer_G.zero_grad()

        # Random sample noise
        z = random_sample_z_space(imgs.size(0))

        # Generate image
        gen_imgs = generator(z)

        # Generator's loss: loss between D(G(z)) and real ground truth
        loss_G = adversarial_loss(discriminator(gen_imgs), real_ground_truth)

        loss_G.backward()
        optimizer_G.step()


        #####################
        # Train Discriminator

        optimizer_D.zero_grad()

        loss_real = adversarial_loss(discriminator(real_imgs), real_ground_truth)
        loss_fake = adversarial_loss(discriminator(gen_imgs.detach()), fake_ground_truth)
        loss_D = (loss_real+loss_fake)/2

        loss_D.backward()
        optimizer_D.step()


        #####################
        # archieve loss
        losses.append([loss_G.item(), loss_D.item()])

        # Print progress
        if idx_batch % 10 == 0:
            print("[Epoch {}/{}] [Batch {}/{}] loss_G: {:.6f}, loss_D: {:.6f}".format(idx_epoch, num_epochs,
                                                                                      idx_batch, len(train_data_loader),
                                                                                      loss_G, loss_D))

        batches_done = idx_epoch * len(train_data_loader) + idx_batch