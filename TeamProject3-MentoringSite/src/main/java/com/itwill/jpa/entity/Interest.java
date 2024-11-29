package com.itwill.jpa.entity;


import com.itwill.jpa.dto.InterestDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Interest")
public class Interest {

    @Id
    @SequenceGenerator(name = "interest_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interest_seq")
    private Long interestNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;
    
    
    public static Interest toEntity(InterestDto interestDto) {
        return Interest.builder()
                .interestNo(interestDto.getInterestNo())
                .user(User.toEntity(interestDto.getUser()))
                .category(Category.toEntity(interestDto.getCategory()))
                .build();
    }
}
