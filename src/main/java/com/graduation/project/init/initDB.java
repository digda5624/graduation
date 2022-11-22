package com.graduation.project.init;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import com.graduation.project.domain.enumtype.PostType;
import com.graduation.project.domain.enumtype.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

//    @PostConstruct
//    public void init() {
//        initService.dbInit();
//    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final EntityManager em;
        private final BCryptPasswordEncoder encoder;

        public void dbInit() {

            User user = User.createUser("asd", "asd", encoder.encode("asd"), "asd", "asd", "asd", Auth.USER);

            Post post1 = Post.createPost("자유1", "자유1", false, PostType.FREE, user);
            Post post2 = Post.createPost("자유2", "자유2", false, PostType.FREE, user);
            Post post3 = Post.createPost("자유3", "자유3", false, PostType.FREE, user);
            Post post4 = Post.createPost("자유4", "자유4", false, PostType.FREE, user);
            Post post5 = Post.createPost("자유5", "자유5", false, PostType.FREE, user);
            Post post6 = Post.createPost("장터1", "장터1", false, PostType.MARKET, user);
            Post post7 = Post.createPost("장터2", "장터2", false, PostType.MARKET, user);
            Post post8 = Post.createPost("장터3", "장터3", false, PostType.MARKET, user);
            Post post9 = Post.createPost("장터4", "장터4", false, PostType.MARKET, user);
            Post post10 = Post.createPost("장터5", "장터5", false, PostType.MARKET, user);
            Post post11 = Post.createPost("구인1", "구인1", false, PostType.RECRUIT, user);
            Post post12 = Post.createPost("구인2", "구인2", false, PostType.RECRUIT, user);
            Post post13 = Post.createPost("구인3", "구인3", false, PostType.RECRUIT, user);
            Post post14 = Post.createPost("구인4", "구인4", false, PostType.RECRUIT, user);
            Post post15 = Post.createPost("구인5", "구인5", false, PostType.RECRUIT, user);

            em.persist(user);
            em.persist(post1);
            em.persist(post2);
            em.persist(post3);
            em.persist(post4);
            em.persist(post5);
            em.persist(post6);
            em.persist(post7);
            em.persist(post8);
            em.persist(post9);
            em.persist(post10);
            em.persist(post11);
            em.persist(post12);
            em.persist(post13);
            em.persist(post14);
            em.persist(post15);
        }
    }

}
