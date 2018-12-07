package info.goodline.department.cardbag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends ABaseActivity {

    private EditText etCardName;
    private EditText etCategory;
    private EditText etDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        initToolbar();
        setToolbarTitle(R.string.title_activity_add_card);
        showBackArrowInToolBar(true);

        etCardName = findViewById(R.id.etCardName);
        etCategory = findViewById(R.id.etCategory);
        etDiscount = findViewById(R.id.etDiscount);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        String nameCardText = etCardName.getText().toString();
        String categoryText = etCategory.getText().toString();
        double discount = Double.parseDouble(etDiscount.getText().toString());

        Category category = new Category(1, categoryText);
        Card card = new Card(1, category, nameCardText, discount);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(Card.class.getSimpleName(), card);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


}
