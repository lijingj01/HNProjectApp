package as.hn.com.hnprojectapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Appplan extends AppCompatActivity {

    @BindView(R.id.txtTitle)EditText txtTitle;
    @BindView(R.id.txtContent)EditText txtContent;
    @BindView(R.id.editText)EditText editText;
    @BindView(R.id.editEndText)EditText editEndText;
    @BindView(R.id.btSave)com.gc.materialdesign.views.ButtonRectangle btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appplan);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
