package com.example.recipeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShoppingListFragment extends Fragment {

    // This layout is the container where all shopping items will be added.
    LinearLayout containerLayout;
    EditText etItemInput; // This is the input field for add new item

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        // I find all the buttons and views from the layout file.
        ImageButton btnBack = view.findViewById(R.id.btnBackShop);
        Button btnAdd = view.findViewById(R.id.btnAddItem); // This button need to be in XML layout
        Button btnClear = view.findViewById(R.id.btnClearList);
        containerLayout = view.findViewById(R.id.shoppingContainer); // This ID need to be in XML layout
        etItemInput = view.findViewById(R.id.etAddItem); // This ID need to be in XML layout

        // 1. I load the saved list when the screen starts.
        loadShoppingList();

        // 2. Action for the Add Item button.
        btnAdd.setOnClickListener(v -> {
            String text = etItemInput.getText().toString().trim();
            if (!text.isEmpty()) {
                ShoppingManager.addItem(text); // I add the new item to my data manager.
                addVisualItem(text); // I draw the new item on the screen.
                etItemInput.setText(""); // I clear the input field after adding.
            } else {
                Toast.makeText(getContext(), "Type something first!", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Action for the Clear All button.
        btnClear.setOnClickListener(v -> {
            ShoppingManager.clearAll(); // I tell the manager to delete all items.
            containerLayout.removeAllViews(); // I remove all the visual items from the screen.
            Toast.makeText(getContext(), "List Cleared!", Toast.LENGTH_SHORT).show();
        });

        // The back button for going back to previous screen.
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }

    // This function gets all items from manager and draws them on screen.
    private void loadShoppingList() {
        containerLayout.removeAllViews(); // I clear old list first.
        // I loop through all saved items and draw them.
        for (String item : ShoppingManager.getList()) {
            addVisualItem(item);
        }
    }

    // This function creates one item row dynamically (CheckBox + Delete Button).
    private void addVisualItem(String text) {
        // The item row (Horizontal Layout)
        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, 15, 0, 15);

        // The CheckBox element.
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setText(text);
        checkBox.setTextSize(18);
        checkBox.setTextColor(Color.BLACK);

        // This layout parameter makes the checkbox take all available space.
        LinearLayout.LayoutParams checkParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        checkBox.setLayoutParams(checkParams);

        // The small delete button (Trash Icon).
        ImageView btnDelete = new ImageView(getContext());
        btnDelete.setImageResource(android.R.drawable.ic_menu_delete); // I use the delete icon
        btnDelete.setColorFilter(Color.RED); // Make the icon red color
        btnDelete.setPadding(15, 15, 15, 15);

        // Action for delete button when user clicks.
        btnDelete.setOnClickListener(v -> {
            ShoppingManager.removeItem(text); // Remove item from my data manager.
            containerLayout.removeView(row);  // Remove the visual row from the screen.
        });

        // I add the CheckBox and the Delete Button to the row layout.
        row.addView(checkBox);
        row.addView(btnDelete);

        // I add the finished row to the main container.
        containerLayout.addView(row);
    }
}