package as.hn.com.hnprojectapp;

import android.app.job.JobInfo;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hn.business.Data.ServiceHelper;
import com.hn.business.Tools.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gc.materialdesign.widgets.Dialog;

public class PwdEditActivity extends MyActivityBase {

    @BindView(R.id.currentUser)
    TextView currentUser;
    @BindView(R.id.oldpassword)
    EditText oldpassword;
    @BindView(R.id.newpassword)
    EditText newpassword;
    @BindView(R.id.verpassword)
    EditText verpassword;
    @BindView(R.id.email_edit_pwd_button)
    Button email_edit_pwd_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_edit);
        ButterKnife.bind(this);

        //当前用户
        currentUser.setText(getMyUser().getUserName());

        email_edit_pwd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        String strUserCode = getMyUser().getUserCode();
        String oldpwd = oldpassword.getText().toString();
        String newpwd = newpassword.getText().toString();
        String verpwd = verpassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(newpwd) && !isPasswordValid(newpwd)) {
            newpassword.setError(getString(R.string.error_invalid_password));
            focusView = newpassword;
            cancel = true;
        }else if(!newpwd.equals(verpwd)){
            verpassword.setError(getString(R.string.error_invalid_verpassword));
            focusView = verpassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            //region 去服务器修改密码并验证
            ServiceHelper serviceHelper = new ServiceHelper();
            if (serviceHelper.UserEditPwd(strUserCode, oldpwd, newpwd)) {
//                showProgress(false);
//                ToastUtils.showToast(this, "修改密码成功！");
                ShowMessage("修改密码成功！",true);
            } else {
//                showProgress(false);
//                ToastUtils.showToast(this, "修改密码失败，原密码错误！");
                ShowMessage("修改密码失败，原密码错误！", false);
            }
        }

    }

    private void ShowMessage(String meg,final boolean isEdit){
        Dialog dialog = new Dialog(PwdEditActivity.this, "操作提示", meg);
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEdit) {
                    ToMain();
                }else{

                }
            }
        });
        dialog.show();
    }
    private void ToMain(){
        //回到主页
        Intent intent = new Intent(PwdEditActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
