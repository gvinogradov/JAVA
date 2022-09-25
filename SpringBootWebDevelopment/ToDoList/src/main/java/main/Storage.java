package main;

import main.core.Task;
import main.core.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Storage {

    private static volatile Long userId = 1L;
    private static Map<Long, User> users = new HashMap<>();
    private static final ReentrantReadWriteLock tasksLock = new ReentrantReadWriteLock();

    private static volatile Long taskId = 1L;
    private static Map<Long, Task> tasks = new HashMap<>();
    private static final ReentrantReadWriteLock usersLock = new ReentrantReadWriteLock();


    public static Task getTask(long taskId) {
        tasksLock.readLock().lock();
        Task task = tasks.get(taskId);
        tasksLock.readLock().unlock();
        return task;
    }

    public static List<Task> getAllTasks() {
        tasksLock.readLock().lock();
        List<Task> taskList = tasks.values().stream().toList();
        tasksLock.readLock().unlock();
        return taskList;
    }

    public synchronized static Task addTask(Task task) {
        tasksLock.writeLock().lock();
        task.setId(taskId);
        tasks.put(taskId, task);
        taskId++;
        tasksLock.writeLock().unlock();
        return task;
    }

    public static Task update(Task task) {
        long id = task.getId();
        if (!tasks.containsKey(id)) {
            return null;
        }
        tasksLock.writeLock().lock();
        tasks.replace(id, task);
        tasksLock.writeLock().unlock();
        return task;
    }

    public static void delete(Task task) {
        tasksLock.writeLock().lock();
        tasks.remove(task.getId());
        tasksLock.writeLock().unlock();
    }

    public static void deleteAllTasks() {
        tasksLock.writeLock().lock();
        tasks = new HashMap<>();
        taskId = 1L;
        tasksLock.writeLock().unlock();
    }

    public static User getUser(long userId) {
        usersLock.readLock().lock();
        User user = users.get(userId);
        usersLock.readLock().unlock();
        return user;
    }

    public static List<User> getAllUsers() {
        usersLock.readLock().lock();
        List<User> userList = users.values().stream().toList();
        usersLock.readLock().unlock();
        return userList;
    }

    public synchronized static User addUser(User user) {
        usersLock.writeLock().lock();
        user.setId(userId);
        users.put(userId, user);
        userId++;
        usersLock.writeLock().unlock();
        return user;
    }

    public static User update(User user) {
        long id = user.getId();
        if (!users.containsKey(id)) {
            return null;
        }
        usersLock.writeLock().lock();
        users.replace(id, user);
        usersLock.writeLock().unlock();
        return user;
    }

    public static void delete(User user) {
        usersLock.writeLock().lock();
        users.remove(user.getId());
        usersLock.writeLock().unlock();
    }

    public static void deleteAllUsers() {
        usersLock.writeLock().lock();
        users = new HashMap<>();
        userId = 1L;
        usersLock.writeLock().unlock();
    }
}
