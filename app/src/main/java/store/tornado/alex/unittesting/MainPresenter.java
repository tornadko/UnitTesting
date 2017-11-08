package store.tornado.alex.unittesting;

import store.tornado.alex.unittesting.generator.StringGenerator;

/**
 * Created by: anna
 * Date: 11/5/17.
 */

public class MainPresenter implements MVP.Presenter {
	private final MVP.Model model;
	private MVP.View view;

	public MainPresenter() {
		this.model = new MainModel(new StringGenerator());
	}

	@Override
	public void onGenerateClicked(String prefix) {
		this.model.generate(prefix);
	}

	@Override
	public void onViewCreated() {
		view.updateResult("please enter prefix");
	}

	@Override
	public void setView(MVP.View view) {
		this.view = view;
	}
}
