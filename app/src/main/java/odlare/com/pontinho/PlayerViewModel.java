package odlare.com.pontinho;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {

    private PlayerRepository playerRepository;
    private LiveData<List<Player>> players;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        this.playerRepository = new PlayerRepository(application);
        this.players = playerRepository.findAll();
    }

    public LiveData<List<Player>> findAll() {
        return this.players;
    }

    public void insert(Player player) {
        playerRepository.insert(player);
    }

    public void deleteAll() {
        playerRepository.deleteAll();
    }

    public void delete(Player player) {
        playerRepository.delete(player);
    }
}
