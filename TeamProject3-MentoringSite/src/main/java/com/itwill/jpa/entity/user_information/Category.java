package com.itwill.jpa.entity.user_information;

import java.util.ArrayList;
import java.util.List;

import com.itwill.jpa.dto.user_information.CategoryDto;

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
@Table(name = "Category")
public class Category {

    @Id
    @SequenceGenerator(name = "category_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_SEQ")
    @Column(name = "category_no")
    private Long categoryNo;
    @Column(name = "category_name" ,nullable = false)
    private String categoryName;
    @Column(name = "category_level" ,nullable = false)
    private Integer categoryLevel;
    
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<MentorProfile> mentorProfiles = new ArrayList<>();
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Interest> interest = new ArrayList<>();
    
    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
        		
        		.categoryNo(categoryDto.getCategoryNo())
                .categoryName(categoryDto.getCategoryName())
                .categoryLevel(categoryDto.getCategoryLevel())
                .build();
    }
}
