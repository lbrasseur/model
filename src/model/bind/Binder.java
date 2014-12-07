package model.bind;

import static com.google.common.base.Preconditions.checkNotNull;
import model.value.Registration;
import model.value.Value;

import com.google.common.base.Function;

public class Binder {

	public static <S, T> Registration bind(final Value<S> source,
			final Value<T> target, final Function<S, T> transformation) {
		checkNotNull(source);
		checkNotNull(target);
		checkNotNull(transformation);
		Runnable binder = new Runnable() {
			@Override
			public void run() {
				target.set(transformation.apply(source.get()));
			}
		};
		binder.run();
		return source.addChangeHandler(binder);
	}

	private Binder() {
	}

}
