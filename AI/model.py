# 필요 라이브러리

import torch
import torch.nn as nn
import torchvision
from torchvision import datasets
import torchvision.transforms as transforms
from torchvision.utils import save_image
import cv2
from torch.utils.data import DataLoader, Dataset
from torch import optim
import torch.nn.functional as F


from PIL import Image
from tqdm import tqdm_notebook as tqdm
import random
from matplotlib import pyplot as plt

# 데이터셋 불러오기 
from PIL import Image
import os

current_directory = os.path.dirname(os.path.realpath(__file__))
evada_directory = os.path.join(current_directory, 'GAN/evada100')
evada_images_filepaths = sorted([os.path.join(evada_directory, f) for f in os.listdir(evada_directory)])
images_filepaths = [*evada_images_filepaths]

correct_images_filepaths = [i for i in images_filepaths if cv2.imread(i) is not None]

random.seed(42)
random.shuffle(correct_images_filepaths)
train_images_filepaths = correct_images_filepaths[:80]
val_images_filepaths = correct_images_filepaths[-20:-10]
test_images_filepaths = correct_images_filepaths[-10:]

# print(len(train_images_filepaths), len(val_images_filepaths),len(test_images_filepaths))

# 시각화
from matplotlib import pyplot as plt

def display_image_grid(images_filepaths, predicted_labels=(), cols=5):

  rows = len(images_filepaths) // cols

  figure, ax = plt.subplots(nrows=rows, ncols=cols, figsize=(12,6))

  for i, image_filepath in enumerate(images_filepaths):
    image = cv2.imread(image_filepath)
    image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

    true_label = os.path.normpath(image_filepath).split(os.sep)[-2]
    predicted_label = predicted_label[i] if predicted_labels else true_label

    color = "green" if true_label==predicted_label else "red"

    ax.ravel()[i].imshow(image)
    ax.ravel()[i].set_title(predicted_label,color=color)
    ax.ravel()[i].set_axis_off()
  
  plt.tight_layout()
  plt.show()
  
#display_image_grid(test_images_filepaths)


# 데이터 전처리
trans = transforms.Compose([transforms.Resize(28),
                            transforms.ToTensor(),
                            transforms.Normalize([0.5],[0.5])])


from torchvision.datasets import ImageFolder
from torch.utils.data.dataloader import DataLoader 

gan_directory = os.path.join(current_directory, 'GAN')
dataset = ImageFolder(
    root = gan_directory,
    transform=trans
)
loader = DataLoader(dataset, batch_size=128, shuffle=True)