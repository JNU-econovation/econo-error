package com.example.demo.auth.application.model;

import com.example.demo.common.application.model.MemberIdModel;
import com.example.demo.common.support.AbstractModel;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberModel implements AbstractModel, MemberIdModel {
    private Long id;
    private String name;
    //private ActiveStatus activeStatus;
    private OauthServerType oauthServerType;

//    public MemberModel updateActiveStatus(String status) {
//        ActiveStatus requestStatus = ActiveStatus.find(status);
//        canEdit(requestStatus);
//        this.activeStatus = requestStatus;
//        return this;
//    }
//
//    public boolean validateSame(Long memberId) {
//        return id.equals(memberId);
//    }
//
//    public String getActiveStatus() {
//        return activeStatus.getStatus();
//    }
//
//    private void canEdit(ActiveStatus requestStatus) {
//        if (requestStatus.isAll()) {
//            throw new DeniedUpdateActiveException(requestStatus.getStatus());
//        }
//    }

    @Override
    public Long getMemberId() {
        return id;
    }
}