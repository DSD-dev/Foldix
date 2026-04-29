// Generated file. Do not modify.
package genius.Veos.Foldix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

public final class MainBinding {
    public final LinearLayout rootView;
    public final LinearLayout linearBg;
    public final LinearLayout linearTop;
    public final TextView titleAppName;
    public final LinearLayout linearAutosortSwitcher;
    public final Switch switchAutoSort;
    public final LinearLayout linearActionButtons;
    public final Button buttonSortNow;
    public final Button buttonOnlyNew;
    public final LinearLayout linearSettingsBg;
    public final LinearLayout linearHeader;
    public final TextView titleSettingsDropDown;
    public final LinearLayout linearDpImg;
    public final ImageView imageviewDropDown;
    public final LinearLayout linearSettings;
    public final LinearLayout linearSortDownloads;
    public final RadioButton radiobuttonSortDownload;
    public final LinearLayout linearSortAll;
    public final RadioButton radiobuttonSortAll;
    public final LinearLayout linearSortevery;
    public final TextInputLayout textinputlayout;
    public final EditText edittextSortEvery;
    public final LinearLayout linearStatistic;
    public final TextView textviewStatistic;
    public final LinearLayout linear4D84NN3R;

    private MainBinding(LinearLayout rootView, LinearLayout linearBg, LinearLayout linearTop, TextView titleAppName, LinearLayout linearAutosortSwitcher, Switch switchAutoSort, LinearLayout linearActionButtons, Button buttonSortNow, Button buttonOnlyNew, LinearLayout linearSettingsBg, LinearLayout linearHeader, TextView titleSettingsDropDown, LinearLayout linearDpImg, ImageView imageviewDropDown, LinearLayout linearSettings, LinearLayout linearSortDownloads, RadioButton radiobuttonSortDownload, LinearLayout linearSortAll, RadioButton radiobuttonSortAll, LinearLayout linearSortevery, TextInputLayout textinputlayout, EditText edittextSortEvery, LinearLayout linearStatistic, TextView textviewStatistic, LinearLayout linear4D84NN3R) {
        this.rootView = rootView;
        this.linearBg = linearBg;
        this.linearTop = linearTop;
        this.titleAppName = titleAppName;
        this.linearAutosortSwitcher = linearAutosortSwitcher;
        this.switchAutoSort = switchAutoSort;
        this.linearActionButtons = linearActionButtons;
        this.buttonSortNow = buttonSortNow;
        this.buttonOnlyNew = buttonOnlyNew;
        this.linearSettingsBg = linearSettingsBg;
        this.linearHeader = linearHeader;
        this.titleSettingsDropDown = titleSettingsDropDown;
        this.linearDpImg = linearDpImg;
        this.imageviewDropDown = imageviewDropDown;
        this.linearSettings = linearSettings;
        this.linearSortDownloads = linearSortDownloads;
        this.radiobuttonSortDownload = radiobuttonSortDownload;
        this.linearSortAll = linearSortAll;
        this.radiobuttonSortAll = radiobuttonSortAll;
        this.linearSortevery = linearSortevery;
        this.textinputlayout = textinputlayout;
        this.edittextSortEvery = edittextSortEvery;
        this.linearStatistic = linearStatistic;
        this.textviewStatistic = textviewStatistic;
        this.linear4D84NN3R = linear4D84NN3R;
    }

    public LinearLayout getRoot() {
        return rootView;
    }

    public static MainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.main, parent, false);
        if (attachToParent) parent.addView(root);
        return bind(root);
    }

    public static MainBinding bind(View view) {
        LinearLayout rootView = (LinearLayout) view;
        LinearLayout linearBg = findChildViewById(view, R.id.linear_bg);
        LinearLayout linearTop = findChildViewById(view, R.id.linear_top);
        TextView titleAppName = findChildViewById(view, R.id.title_app_name);
        LinearLayout linearAutosortSwitcher = findChildViewById(view, R.id.linear_autosort_switcher);
        Switch switchAutoSort = findChildViewById(view, R.id.switch_auto_sort);
        LinearLayout linearActionButtons = findChildViewById(view, R.id.linear_action_buttons);
        Button buttonSortNow = findChildViewById(view, R.id.button_sort_now);
        Button buttonOnlyNew = findChildViewById(view, R.id.button_only_new);
        LinearLayout linearSettingsBg = findChildViewById(view, R.id.linear_settings_bg);
        LinearLayout linearHeader = findChildViewById(view, R.id.linear_header);
        TextView titleSettingsDropDown = findChildViewById(view, R.id.title_settings_drop_down);
        LinearLayout linearDpImg = findChildViewById(view, R.id.linear_dp_img);
        ImageView imageviewDropDown = findChildViewById(view, R.id.imageview_drop_down);
        LinearLayout linearSettings = findChildViewById(view, R.id.linear_settings);
        LinearLayout linearSortDownloads = findChildViewById(view, R.id.linear_sort_downloads);
        RadioButton radiobuttonSortDownload = findChildViewById(view, R.id.radiobutton_sort_download);
        LinearLayout linearSortAll = findChildViewById(view, R.id.linear_sort_all);
        RadioButton radiobuttonSortAll = findChildViewById(view, R.id.radiobutton_sort_all);
        LinearLayout linearSortevery = findChildViewById(view, R.id.linear_sortevery);
        TextInputLayout textinputlayout = findChildViewById(view, R.id.textinputlayout);
        EditText edittextSortEvery = findChildViewById(view, R.id.edittextSortEvery);
        LinearLayout linearStatistic = findChildViewById(view, R.id.linear_statistic);
        TextView textviewStatistic = findChildViewById(view, R.id.textview_statistic);
        LinearLayout linear4D84NN3R = findChildViewById(view, R.id.linear_4D_84NN3R);

        if (linearBg == null || linearTop == null || titleAppName == null || linearAutosortSwitcher == null || switchAutoSort == null || linearActionButtons == null || buttonSortNow == null || buttonOnlyNew == null || linearSettingsBg == null || linearHeader == null || titleSettingsDropDown == null || linearDpImg == null || imageviewDropDown == null || linearSettings == null || linearSortDownloads == null || radiobuttonSortDownload == null || linearSortAll == null || radiobuttonSortAll == null || linearSortevery == null || textinputlayout == null || edittextSortEvery == null || linearStatistic == null || textviewStatistic == null || linear4D84NN3R == null) {
             throw new IllegalStateException("Required views are missing");
        }

        return new MainBinding(rootView, linearBg, linearTop, titleAppName, linearAutosortSwitcher, switchAutoSort, linearActionButtons, buttonSortNow, buttonOnlyNew, linearSettingsBg, linearHeader, titleSettingsDropDown, linearDpImg, imageviewDropDown, linearSettings, linearSortDownloads, radiobuttonSortDownload, linearSortAll, radiobuttonSortAll, linearSortevery, textinputlayout, edittextSortEvery, linearStatistic, textviewStatistic, linear4D84NN3R);
    }

    private static <T extends View> T findChildViewById(View rootView, int id) {
         if (rootView instanceof ViewGroup) {
              ViewGroup rootViewGroup = (ViewGroup) rootView;
              for (int i = 0; i < rootViewGroup.getChildCount(); i++) {
                   T view = rootViewGroup.getChildAt(i).findViewById(id);
                   if (view != null) return view;
              }
         }
         return null;
    }
}