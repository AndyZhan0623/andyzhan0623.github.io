package andyzhan.gamebox.domain.game.blackjack.match;

import andyzhan.gamebox.infrastructure.database.entity.User;

import java.util.ArrayList;
import java.util.List;

public class BlackjackRoom
{
    private final List<User> users = new ArrayList<>();

    public void addUser(User user)
    {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int index){
        return users.get(index);
    }
}
