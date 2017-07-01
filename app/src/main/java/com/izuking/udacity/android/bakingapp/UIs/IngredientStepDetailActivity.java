package com.izuking.udacity.android.bakingapp.UIs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.izuking.udacity.android.bakingapp.R;
import com.izuking.udacity.android.bakingapp.data.Ingredient;
import com.izuking.udacity.android.bakingapp.data.Step;

import java.util.ArrayList;

import static com.izuking.udacity.android.bakingapp.UIs.IngredientStepActivity.PANES;
import static com.izuking.udacity.android.bakingapp.UIs.IngredientStepActivity.POSITION;
import static com.izuking.udacity.android.bakingapp.UIs.IngredientStepAdapter.INGREDIENTS;
import static com.izuking.udacity.android.bakingapp.UIs.IngredientStepAdapter.STEPS;


public class IngredientStepDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_step_detail);
        if (savedInstanceState == null) {
            if (getIntent().getExtras() != null) {
                Bundle extras = getIntent().getExtras();
                if (extras.containsKey(INGREDIENTS)) {
                    ArrayList<Ingredient> ingredients = getIntent().getParcelableArrayListExtra(INGREDIENTS);
                    ArrayList<Step> steps = getIntent().getParcelableArrayListExtra(STEPS);
                    int position = getIntent().getIntExtra(POSITION, 0);
                    boolean mTwoPane = getIntent().getBooleanExtra(PANES, false);
                    IngredientStepDetailFragment fragment = new IngredientStepDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(POSITION, position);
                    bundle.putBoolean(PANES, mTwoPane);
                    bundle.putParcelableArrayList(INGREDIENTS, ingredients);
                    bundle.putParcelableArrayList(STEPS, steps);
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.step_container, fragment)
                            .commit();
                }
            }


        }
    }

}
