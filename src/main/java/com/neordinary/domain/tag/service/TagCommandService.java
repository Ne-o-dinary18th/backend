package com.neordinary.domain.tag.service;

import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.User;
import com.neordinary.domain.tag.repository.TagRepository;
import com.neordinary.domain.tag.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagCommandService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public Tag createTag(String tagName) {
        var tag = Tag.builder().title(tagName).build();
        tagRepository.save(tag);
        return tag;
    }

    public User createUser(Long tagId, String userName) {
        // find tag
        var tag = tagRepository.findById(tagId).get();
        // Todo: 에러 처리
        var user = User
                .builder()
                .name(userName)
                .tag(tag)
                .build();
        userRepository.save(user);
        return user;
    }
}
