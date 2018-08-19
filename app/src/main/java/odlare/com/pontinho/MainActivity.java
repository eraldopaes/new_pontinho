package odlare.com.pontinho;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PlayerListAdapter.PlayerOnClickHandle {

    public static final int NEW_PLAYER_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_PLAYER_ACTIVITY_REQUEST_CODE = 2;

    PlayerListAdapter playerListAdapter;
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        playerListAdapter = new PlayerListAdapter(this, this);
        recyclerView.setAdapter(playerListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playerViewModel.findAll().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                Log.i("Alteração no banco", "--->");
                playerListAdapter.setPlayers(players);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_options);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewPlayerActivity.class);
                startActivityForResult(intent, NEW_PLAYER_ACTIVITY_REQUEST_CODE);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int adapterPosition = viewHolder.getAdapterPosition();
                Player player = playerListAdapter.getPlayerAtPosition(adapterPosition);
                playerViewModel.delete(player);
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.pontinho_main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.delete_all_players:
                playerViewModel.deleteAll();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == NEW_PLAYER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String name = data.getStringExtra("name");
            String lastname = data.getStringExtra("lastname");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");

            Player player = new Player(name, lastname, phone, email);
            playerViewModel.insert(player);

        } else if (requestCode == UPDATE_PLAYER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Integer id = data.getIntExtra("id", -1);
            String name = data.getStringExtra("name");
            String lastname = data.getStringExtra("lastname");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");

            Player player = new Player(id, name, lastname, phone, email);
            playerViewModel.update(player);
        }
    }

    @Override
    public void onClick(int position) {

        Player playerAtPosition = playerListAdapter.getPlayerAtPosition(position);

        Intent intent = new Intent(getApplicationContext(), NewPlayerActivity.class);

        intent.putExtra("id", playerAtPosition.getId());
        intent.putExtra("name", playerAtPosition.getName());
        intent.putExtra("lastname", playerAtPosition.getLastname());
        intent.putExtra("email", playerAtPosition.getEmail());
        intent.putExtra("phone", playerAtPosition.getPhone());

        startActivityForResult(intent, UPDATE_PLAYER_ACTIVITY_REQUEST_CODE);
    }
}
