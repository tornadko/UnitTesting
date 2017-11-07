package store.tornado.alex.unittesting;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import store.tornado.alex.unittesting.generator.Generator;

public class MainActivity extends RoboActivity implements MVP.View {

	@Inject
	public Generator generator;

	private MVP.Presenter presenter;
	private TextView tvResult;
	private EditText etPrefix;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RoboGuice.getInjector(this).injectMembersWithoutViews(this);

		this.presenter = new MainPresenter(new MainModel(generator), this);
		setContentView(R.layout.activity_main);
		tvResult = findViewById(R.id.result);
		etPrefix = findViewById(R.id.prefix);
		findViewById(R.id.button).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				presenter.onGenerateClicked(String.valueOf(etPrefix.getText()));
			}
		});
		this.presenter.onViewCreated();
	}

	@Override
	public void updateResult(String result) {
		tvResult.setText(result);
	}
}
