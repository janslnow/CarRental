package com.demo.rental.common;

import com.demo.rental.model.User;

public class UserContext {

    private static final ThreadLocal<User> USER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();


    public static void serUser(User user) {
        USER_CONTEXT_THREAD_LOCAL.set(user);
    }

    public static void clear() {
        USER_CONTEXT_THREAD_LOCAL.remove();
    }

    public static User getUser() {
        return USER_CONTEXT_THREAD_LOCAL.get();
    }

    public static Integer getUserId() {

        User user = getUser();

        return user == null ? null : user.getUserId();
    }

    public static String getUserName() {

        User user = getUser();

        return user == null ? null : user.getUserName();
    }
}
