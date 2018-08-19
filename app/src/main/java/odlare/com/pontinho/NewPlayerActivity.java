package odlare.com.pontinho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText lastname;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);

        name = findViewById(R.id.edt_first_name);
        lastname = findViewById(R.id.edt_last_name);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.button:

                Intent intent = new Intent();

                intent.putExtra("name", name.getText().toString());
                intent.putExtra("lastname", lastname.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("phone", phone.getText().toString());

                setResult(RESULT_OK, intent);

                finish();

                break;
        }
    }
}
