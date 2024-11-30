package com.example.demo.auth.persistence;


import com.example.demo.filter.persistence.FilterEntity;
import lombok.*;


@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberModel {
    private Long id;
    private String name;
    private final MemberRepository memberRepository;

    public MemberEntity getMemberModel(Long memberId) {
        // 데이터베이스에서 filterId로 FilterEntity 조회
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Filter not found with id: " + memberId));
    }
}
