package org.example;

import org.example.dao.Impl.ProfileDaoImpl;
import org.example.dao.ProfileDao;
import org.example.entities.Comment;
import org.example.entities.Post;
import org.example.entities.Profile;
import org.example.entities.User;
import org.example.enums.Gender;
import org.example.service.CommentService;
import org.example.service.Impl.CommentServiceImpl;
import org.example.service.Impl.PostServiceImpl;
import org.example.service.Impl.ProfileServiceImpl;
import org.example.service.Impl.UserServiceImpl;
import org.example.service.PostService;
import org.example.service.ProfileService;
import org.example.service.UserService;
import lombok.Builder;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        ProfileService profileService = new ProfileServiceImpl();
        PostService postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();
//-------------------------------------------------------------------------------------------------------------
        //todo save user
//    first Method
        //        userService.save(User.builder()
//                .username("sf")
//                .email("asf@gmail.com")
//                .password("MyPass")
//                .profile(
//                        new Profile(
//                                "profile", LocalDate.of(2003, 9, 1), Gender.FEMALE, "dgrh"
//                        )
//                )
//                .build());

//   second Method
//        userService.save(
//                new User(
//                        "Ainura", "aika@gmail.com", "123srt",
//                        new Profile(
//                                "Profile", LocalDate.of(2003, 12, 9), Gender.FEMALE, "Biography"
//                        )
//                )
//        );

        //todo getUser
//        System.out.println(userService.findByID(4L));

        //todo delete User
//        userService.deletedByID(1L);
//-------------------------------------------------------------------------------------------------------------



        //todo save Profile
//        userService.save(new User("aika", "a@gmail.com", "sdfsdf"));
//        profileService.save(4L, new Profile("prof", LocalDate.of(2000, 9, 1), Gender.FEMALE, "bio"));

        //todo delete Profile
//        profileService.deleteByID(2L);

        //todo findProfileByUserId
//        profileService.findProfileByUserId(4L);

        //todo updateUserProfile
//        System.out.println(profileService.updateUserProfile(4L, new Profile("PPP", LocalDate.of(2025, 8, 1), Gender.FEMALE, "biography")));

//-------------------------------------------------------------------------------------------------------------



        //todo save Post
//        postService.savePost(1L, Post
//                .builder()
//                        .image("sdfgf")
//                        .description("sggseg")
//                        .createdAt(LocalDate.of(2003, 9,3))
//                .build());

        //todo getPostByUserId
//        postService.getPostByUserId(1L);

        //todo searchPost
//        System.out.println(postService.searchPost("sggseg"));

        //todo assign post to user
//        System.out.println(postService.assignPostToUser(4L, 1L));

        //todo delete postById
//        postService.deleteById(1L);

        //todo findByID
//        System.out.println(postService.findById(1L));

// -------------------------------------------------------------------------------------------------------------



        //todo saveComment
//        System.out.println(commentService.saveComment(1L, 1L, new Comment("about winter", LocalDate.of(2025, 2, 1))));
//        System.out.println(commentService.saveComment(1L, 1L, new Comment("about job", LocalDate.of(2025, 2, 1))));


        //todo findCommentByPostId
//        try {
//            System.out.println(commentService.findCommentByPostId(1L));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        //todo updateComment
//        try {
//            System.out.println(commentService.updateComment(1L, "about Summer"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        //todo deleteComment
//        try {
//            System.out.println(commentService.deleteComment(
//                    new Comment(1L, "about Summer", LocalDate.of(2025, 2, 1))
//            ));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
