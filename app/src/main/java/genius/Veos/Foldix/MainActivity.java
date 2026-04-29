package genius.Veos.Foldix;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.provider.Settings;
import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import android.content.res.ColorStateList;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;

public class MainActivity extends AppCompatActivity {
	
	private MainBinding binding;
	
	private ObjectAnimator obj1 = new ObjectAnimator();
	private ObjectAnimator obj2 = new ObjectAnimator();
	private SharedPreferences sp_autosort;
	private SharedPreferences sp_settings;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		
		MobileAds.initialize(this);
		
		List<String> testDeviceIds = Arrays.asList("74728E1E9ABA0C546837292BCECFC686");
		MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build());
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		sp_autosort = getSharedPreferences("auto_sort", Activity.MODE_PRIVATE);
		sp_settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
		
		binding.buttonSortNow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_startSort(false);
			}
		});
		
		binding.buttonOnlyNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_startSort(true);
			}
		});
	}
	
	private void initializeLogic() {
		binding.linearSettings.setVisibility(View.GONE);
		
		ColorStateList radioTint = ColorStateList.valueOf(0xFFC5B8F8);
		binding.radiobuttonSortDownload.setButtonTintList(radioTint);
		binding.radiobuttonSortAll.setButtonTintList(radioTint);
		
		boolean autoSort = sp_autosort.getBoolean("sp_autosort", true);
		boolean sortDownload = sp_settings.getBoolean("sp_settings", true);
		
		binding.switchAutoSort.setChecked(autoSort);
		binding.radiobuttonSortDownload.setChecked(sortDownload);
		binding.radiobuttonSortAll.setChecked(!sortDownload);
		
		int savedDays = sp_settings.getInt("sort_every", 0);
		if (savedDays > 0) binding.edittextSortEvery.setText(String.valueOf(savedDays));
		
		final boolean[] settingsOpen = {false};
		
		_updateStatistic();
		
		binding.titleSettingsDropDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!settingsOpen[0]) {
					binding.linearSettings.setVisibility(View.VISIBLE);
					binding.linearSettings.setAlpha(0f);
					binding.linearSettings.setTranslationY(-40f);
					
					obj1 = ObjectAnimator.ofFloat(binding.imageviewDropDown, "rotation", 0f, 180f);
					obj1.setDuration(300);
					obj1.setInterpolator(new AccelerateDecelerateInterpolator());
					obj1.start();
					
					obj2 = ObjectAnimator.ofFloat(binding.linearSettings, "alpha", 0f, 1f);
					obj2.setDuration(300);
					obj2.start();
					
					ObjectAnimator transAnim = ObjectAnimator.ofFloat(binding.linearSettings, "translationY", -40f, 0f);
					transAnim.setDuration(300);
					transAnim.setInterpolator(new DecelerateInterpolator());
					transAnim.start();
					
					settingsOpen[0] = true;
				} else {
					obj1 = ObjectAnimator.ofFloat(binding.imageviewDropDown, "rotation", 180f, 0f);
					obj1.setDuration(300);
					obj1.setInterpolator(new AccelerateDecelerateInterpolator());
					obj1.start();
					
					ObjectAnimator transAnim = ObjectAnimator.ofFloat(binding.linearSettings, "translationY", 0f, -40f);
					transAnim.setDuration(250);
					transAnim.setInterpolator(new AccelerateInterpolator());
					transAnim.start();
					
					obj2 = ObjectAnimator.ofFloat(binding.linearSettings, "alpha", 1f, 0f);
					obj2.setDuration(250);
					obj2.addListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							binding.linearSettings.setVisibility(View.GONE);
							binding.linearSettings.setTranslationY(0f); // сброс для следующего открытия
						}
					});
					obj2.start();
					
					settingsOpen[0] = false;
				}
			}
		});
		
		binding.switchAutoSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _btn, boolean _checked) {
				sp_autosort.edit().putBoolean("sp_autosort", _checked).apply();
			}
		});
		
		binding.radiobuttonSortDownload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.radiobuttonSortDownload.setChecked(true);
				binding.radiobuttonSortAll.setChecked(false);
				sp_settings.edit().putBoolean("sp_settings", true).apply();
			}
		});
		
		binding.radiobuttonSortAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.radiobuttonSortAll.setChecked(true);
				binding.radiobuttonSortDownload.setChecked(false);
				sp_settings.edit().putBoolean("sp_settings", false).apply();
			}
		});
		
		binding.edittextSortEvery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView _tv, int _actionId, android.view.KeyEvent _event) {
				if (_actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
					try {
						String val = _tv.getText().toString().trim();
						int days = val.isEmpty() ? 0 : Integer.parseInt(val);
						sp_settings.edit().putInt("sort_every", days).apply();
					} catch (NumberFormatException e) {
						_tv.setError("Введите число");
					}
				}
				return false;
			}
		});
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			if (!Environment.isExternalStorageManager()) {
				Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
				intent.setData(Uri.parse("package:" + getPackageName()));
				startActivityForResult(intent, 100);
			} else {
				_requestMediaPermissions();
			}
		} else {
			if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
			!= PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this,
				new String[]{
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE
				}, 101);
			}
		}
		_fonts();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		if (_requestCode == 100) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
				_requestMediaPermissions();
			}
		}
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			if (Environment.isExternalStorageManager()) {
				startService(new Intent(this, SortService.class));
			}
		} else {
			if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
			== PackageManager.PERMISSION_GRANTED) {
				startService(new Intent(this, SortService.class));
			}
		}
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}
	public void _requestMediaPermissions() {
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			ActivityCompat.requestPermissions(this,
			new String[]{
				Manifest.permission.READ_MEDIA_IMAGES,
				Manifest.permission.READ_MEDIA_VIDEO,
				Manifest.permission.READ_MEDIA_AUDIO
			}, 102);
		}
		
	}
	
	
	public void _startSort(final boolean _onlyNew) {
		binding.buttonSortNow.setEnabled(false);
		binding.buttonOnlyNew.setEnabled(false);
		binding.buttonSortNow.setAlpha(_onlyNew ? 0.5f : 1f);
		binding.buttonOnlyNew.setAlpha(_onlyNew ? 1f : 0.5f);
		binding.buttonSortNow.setText(_onlyNew ? "Отсортировать сейчас" : "● Сортировка...");
		binding.buttonOnlyNew.setText(_onlyNew ? "● Только новые файлы" : "Только новые файлы");
		
		View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sort_progress, null);
		
		LinearProgressIndicator progressBar = dialogView.findViewById(R.id.dialog_progress);
		TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
		TextView dialogSubtitle = dialogView.findViewById(R.id.dialog_subtitle);
		TextView dialogProgressText = dialogView.findViewById(R.id.dialog_progress_text);
		ImageView dialogIcon = dialogView.findViewById(R.id.dialog_icon);
		
		dialogIcon.setImageResource(R.drawable.icon_radar_round);
		
		AlertDialog dialog = new AlertDialog.Builder(this)
		.setView(dialogView)
		.setCancelable(false)
		.create();
		if (dialog.getWindow() != null) {
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}
		dialog.show();
		
		Handler mainHandler = new Handler(Looper.getMainLooper());
		
		if (_onlyNew) {
			dialogTitle.setText("Готово");
			dialogSubtitle.setText("Сервис следит за новыми файлами");
			progressBar.setProgressCompat(100, true);
			dialogProgressText.setText("Автосортировка активна");
			mainHandler.postDelayed(() -> {
				dialog.dismiss();
				_resetButtons();
			}, 1200);
			return;
		}
		
		boolean sortDownload = sp_settings.getBoolean("sp_settings", true);
		File rootDir = sortDownload
		? Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
		: Environment.getExternalStorageDirectory();
		
		Set<String> reserved = new HashSet<>(Arrays.asList("Images", "Video", "Music", "Archives", "APK", "Docs", "Torrents", "Code"));
		
		new Thread(() -> {
			List<File> files = new ArrayList<>();
			File[] top = rootDir.listFiles();
			if (top != null) {
				for (File f : top) {
					if (f.isFile()) files.add(f);
				}
			}
			int total = files.size();
			
			runOnUiThread(() -> {
				dialogTitle.setText("Сканирование...");
				dialogSubtitle.setText("Найдено файлов: " + total);
				dialogProgressText.setText("0 / " + total);
				progressBar.setProgressCompat(0, false);
			});
			
			if (total == 0) {
				runOnUiThread(() -> {
					dialogTitle.setText("Готово!");
					dialogSubtitle.setText("Нечего сортировать");
					progressBar.setProgressCompat(100, true);
					mainHandler.postDelayed(() -> {
						dialog.dismiss();
						_resetButtons();
					}, 1500);
				});
				return;
			}
			
			runOnUiThread(() -> {
				dialogTitle.setText("Сортировка...");
				dialogIcon.setImageResource(R.drawable.icon_sort_round);
			});
			
			int sorted = 0;
			for (int i = 0; i < total; i++) {
				File f = files.get(i);
				File parent = f.getParentFile();
				if (parent != null && reserved.contains(parent.getName())) continue;
				SortService.sortFile(f);
				sorted++;
				
				final int progress = (int)((i + 1f) / total * 100);
				final int s = sorted;
				runOnUiThread(() -> {
					progressBar.setProgressCompat(progress, true);
					dialogProgressText.setText(s + " / " + total + " отсортировано");
				});
			}
			
			final int finalSorted = sorted;
			runOnUiThread(() -> {
				dialogTitle.setText("Готово!");
				dialogSubtitle.setText("Отсортировано: " + finalSorted);
				progressBar.setProgressCompat(100, true);
				
				int prevSorted = sp_settings.getInt("total_sorted", 0);
				int prevFolders = sp_settings.getInt("total_folders", 0);
				
				Set<String> usedFolders = new HashSet<>();
				for (File f : files) {
					String dest = SortService.getDestFolder(f.getName().toLowerCase());
					if (dest != null) usedFolders.add(dest);
				}
				
				sp_settings.edit()
				.putInt("total_sorted", prevSorted + finalSorted)
				.putInt("total_folders", prevFolders + usedFolders.size())
				.apply();
				
				_updateStatistic();
				
				mainHandler.postDelayed(() -> {
					dialog.dismiss();
					_resetButtons();
				}, 1500);
			});
		}).start();
	}
	
	
	public void _resetButtons() {
		
		binding.buttonSortNow.setEnabled(true);
		binding.buttonOnlyNew.setEnabled(true);
		binding.buttonSortNow.setAlpha(1f);
		binding.buttonOnlyNew.setAlpha(1f);
		binding.buttonSortNow.setText("Отсортировать сейчас");
		binding.buttonOnlyNew.setText("Только новые файлы");
		
	}
	
	
	public void _updateStatistic() {
		
		int sorted = sp_settings.getInt("total_sorted", 0);
		int folders = sp_settings.getInt("total_folders", 0);
		binding.textviewStatistic.setText("Отсортировано файлов: " + sorted + "\n\nПапок создано: " + folders);
		
	}
	
	
	public void _fonts() {
		Typeface nunito = Typeface.createFromAsset(getAssets(), "fonts/unito_wght.ttf");
		Typeface nunito_b = Typeface.createFromAsset(getAssets(), "fonts/unito_bold.ttf");
		
		binding.titleAppName.setTypeface(nunito_b, Typeface.BOLD);
		binding.switchAutoSort.setTypeface(nunito, Typeface.NORMAL);
		binding.buttonSortNow.setTypeface(nunito_b, Typeface.BOLD);
		binding.buttonOnlyNew.setTypeface(nunito_b, Typeface.BOLD);
		binding.titleSettingsDropDown.setTypeface(nunito_b, Typeface.BOLD);
		binding.radiobuttonSortDownload.setTypeface(nunito, Typeface.NORMAL);
		binding.radiobuttonSortAll.setTypeface(nunito, Typeface.NORMAL);
		binding.edittextSortEvery.setTypeface(nunito, Typeface.NORMAL);
		binding.textviewStatistic.setTypeface(nunito, Typeface.NORMAL);
	}
	
}