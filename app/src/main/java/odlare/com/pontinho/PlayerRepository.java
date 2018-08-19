package odlare.com.pontinho;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PlayerRepository {

    private PlayerDAO playerDAO;
    private LiveData<List<Player>> players;

    public PlayerRepository(Application application) {
        PlayerRoomDatabase db = PlayerRoomDatabase.getDatabase(application);
        this.playerDAO = db.playerDAO();
        this.players = playerDAO.findAll();
    }

    public LiveData<List<Player>> findAll() {
        return this.players;
    }

    public void insert(Player player) {
        new InsertAsyncTask(this.playerDAO).execute(player);
    }

    public void deleteAll() {
        new DeleteAllPlayersAsyncTask(playerDAO).execute();
    }

    public void delete(Player player) {
        new DeleteAsyncTask(playerDAO).execute(player);
    }

    public void update(Player player) {
        new UpdateAsyncTask(playerDAO).execute(player);
    }

    private static class UpdateAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDAO dao;

        private UpdateAsyncTask(PlayerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            dao.update(players[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDAO dao;

        private DeleteAsyncTask(PlayerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            dao.delete(players[0]);
            return null;
        }
    }

    private static class DeleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {

        private PlayerDAO dao;

        DeleteAllPlayersAsyncTask(PlayerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDAO dao;

        InsertAsyncTask(PlayerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            dao.save(players[0]);
            return null;
        }
    }
}
