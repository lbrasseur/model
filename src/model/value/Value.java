package model.value;

import java.util.Objects;

import javax.annotation.Nullable;

import rx.Observable;
import rx.Subscriber;
import rx.observers.Subscribers;

public class Value<T> {
	private T value;
	private Subscriber<? super T> subscriber;

	@Nullable
	public T get() {
		return value;
	}

	public void set(@Nullable T value) {
		T oldValue = this.value;
		this.value = value;
		if (!Objects.equals(oldValue, value) && subscriber != null
				&& !subscriber.isUnsubscribed()) {
			subscriber.onNext(value);
		}
	}

	public Observable<T> asObservable() {
		return Observable.create((Subscriber<? super T> aSubscriber) -> {
			subscriber = aSubscriber;
			subscriber.onNext(value);
		});
	}

	public Subscriber<T> asSubscriber() {
		return Subscribers.create((T value) -> set(value));
	}

	public boolean isNull() {
		return value == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, subscriber);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Value<T> that = (Value<T>) o;
		return Objects.equals(value, that.value)
				&& Objects.equals(subscriber, that.subscriber);
	}

	@Override
	public String toString() {
		return "Value [value=" + value + ", subscriber=" + subscriber + "]";
	}
}
