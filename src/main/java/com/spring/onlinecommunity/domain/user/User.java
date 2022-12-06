package com.spring.onlinecommunity.domain.user;

import com.spring.onlinecommunity.domain.board.Board;
import com.spring.onlinecommunity.domain.both.TimeSetting;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

import static jakarta.persistence.FetchType.*;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeSetting {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(targetEntity = User.class, fetch = LAZY, mappedBy = "id")
    private final Set<User> following = new LinkedHashSet<>();

    @OneToMany(targetEntity = User.class, fetch = LAZY, mappedBy = "id")
    private final Set<User> follower = new LinkedHashSet<>();

    @OneToMany(targetEntity = Board.class, fetch = LAZY, mappedBy = "id")
    private final Set<Board> boards = new LinkedHashSet<>();

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User createUser(UserVO userVO) {
        String username = userVO.username();
        String password = userVO.password();
        return new User(username, password);
    }

    /**
     * 서비스 로직
     * @param user 팔로잉하는 유저
     */
    public void addFollow(User user) {
        this.following.add(user);
        user.follower.add(user);
    }

    public void removeFollow(User user) {
        this.following.remove(user);
        user.follower.remove(user);
    }

    public int getFollowingSize() {
        return this.following.size();
    }

    public int getFollowerSize() {
        return this.follower.size();
    }
}
