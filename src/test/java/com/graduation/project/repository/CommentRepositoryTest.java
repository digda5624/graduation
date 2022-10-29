package com.graduation.project.repository;

import com.graduation.project.domain.Comment;
import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void Null아님() {
        assertThat(commentRepository).isNotNull();
        assertThat(postRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }

    @Test
    void comment있는post조회_user별() {
        // given
        User user1 = User.builder()
                .id(1L)
                .loginId("firstID")
                .nickname("first")
                .name("first")
                .password("1234")
                .build();

        User user2 = User.builder()
                .id(2L)
                .loginId("secondID")
                .nickname("second")
                .name("second")
                .password("1234")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        Post firstPostByFirst = Post.builder()
                .id(3L)
                .title("firstPostByFirst")
                .content("This is first post by firstID")
                .user(user1)
                .build();
        Post secondPostByFirst = Post.builder()
                .id(4L)
                .title("secondPostByFirst")
                .content("This is second post by firstID")
                .user(user1)
                .build();
        Post firstPostBySecond = Post.builder()
                .id(5L)
                .title("firstPostBySecond")
                .content("This is first post by secondID")
                .user(user2)
                .build();
        Post secondPostBySecond = Post.builder()
                .id(6L)
                .title("secondPostBySecond")
                .content("This is second post by secondID")
                .user(user2)
                .build();
        postRepository.save(firstPostByFirst);
        postRepository.save(secondPostByFirst);
        postRepository.save(firstPostBySecond);
        postRepository.save(secondPostBySecond);
        Comment user2commentPost1 = Comment.builder()
                .id(7L)
                .post(firstPostByFirst)
                .user(user2)
                .build();
        Comment user2commentPost2 = Comment.builder()
                .id(8L)
                .post(secondPostByFirst)
                .user(user2)
                .build();
        Comment user1commentPost1 = Comment.builder()
                .id(9L)
                .post(firstPostByFirst)
                .user(user1)
                .build();
        commentRepository.save(user1commentPost1);
        commentRepository.save(user2commentPost1);
        commentRepository.save(user2commentPost2);

        // when
        List<Comment> byUser2 = commentRepository.findByUser(user2.getId());

        // then
        assertThat(byUser2.size()).isEqualTo(2);
        System.out.println("byUser2.get(0).getPost().getTitle() = " + byUser2.get(0).getPost().getTitle());
    }

}