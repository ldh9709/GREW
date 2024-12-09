package com.itwill.jpa.entity.member_information;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.itwill.jpa.dto.member_information.CategoryRequestDto;

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
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(name = "category_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_no_SEQ")
    @Column(name = "category_no")
    private Long categoryNo;
    @Column(name = "category_name" ,nullable = false)
    private String categoryName;
    @Column(name = "category_depth" ,nullable = false)
    private Integer categoryDepth;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_no")
    private Category parentCategory;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<MentorProfile> mentorProfiles = new ArrayList<>();
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Interest> interests = new ArrayList<>();
    
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Category> childCategories = new ArrayList<>();
    
    @PrePersist
    public void setDefaultValues() {
    	if(this.categoryName == null) this.categoryName="";
    	if(this.categoryDepth == null) this.categoryDepth = 2;
    }
    
    
    public static Category toEntity(CategoryRequestDto categoryDto) {
        return Category.builder()
        		.categoryNo(categoryDto.getCategoryNo())
                .categoryName(categoryDto.getCategoryName())
                .categoryDepth(categoryDto.getCategoryDepth())
                .parentCategory(Category.builder()
                		.categoryNo(categoryDto.getCategoryNo())
                		.build())
                .build();
    }
    
    
}
