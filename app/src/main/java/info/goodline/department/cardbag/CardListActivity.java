package info.goodline.department.cardbag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CardListActivity extends ABaseActivity {

    private final int REQUEST_CODE_ADD_CARD = 1;
    private final int STATE_VIEW_EMPTY = 1;
    private final int STATE_VIEW_LIST_ITEMS = 2;

    private LinearLayout llEmptyState;
    private LinearLayout llCard;
    private TextView tvCardName;
    private TextView tvCategoryName;
    private TextView tvDiscount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        initToolbar();
        setToolbarTitle(R.string.title_activity_card_list);

        findViewById(R.id.fabAddCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCardScreen();
            }
        });

        llEmptyState = findViewById(R.id.llEmptyState);
        llCard = findViewById(R.id.llCard);

        tvCardName = findViewById(R.id.tvCardName);
        tvCategoryName = findViewById(R.id.tvCategoryName);
        tvDiscount = findViewById(R.id.tvDiscount);

        changeStateView(STATE_VIEW_EMPTY);
    }

    private void changeStateView(int stateView) {
        boolean isVisibleEmpty = false;
        boolean isVisibleCards = false;

        switch (stateView) {
            case STATE_VIEW_EMPTY:
                isVisibleEmpty = true;
                isVisibleCards = false;
                break;
            case STATE_VIEW_LIST_ITEMS:
                isVisibleEmpty = false;
                isVisibleCards = true;
                break;
        }

        setVisible(llEmptyState, isVisibleEmpty);
        setVisible(llCard, isVisibleCards);
    }

    private void openAddCardScreen() {
        Intent intent = new Intent(this, AddCardActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_CARD);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_card_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miUserProfile:
                Toast.makeText(this, "Мой профиль", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Log.e(tag, "Result code is not OK");
            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_ADD_CARD:
                if (data == null) {
                    Log.e(tag, "Data from activity is null");
                    return;
                }

                Card card = data.getParcelableExtra(Card.class.getSimpleName());
                if (card == null) {
                    Log.e(tag, "Card is null");
                    return;
                }

                tvCardName.setText(card.getName());
                tvCategoryName.setText(card.getCategory().getName());
                tvDiscount.setText(card.getDiscountFormated());
                changeStateView(STATE_VIEW_LIST_ITEMS);
                break;
        }
    }
}
