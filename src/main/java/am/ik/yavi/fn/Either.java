/*
 * Copyright (C) 2018-2022 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.ik.yavi.fn;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import am.ik.yavi.jsr305.Nullable;

public final class Either<L, R> {
	final L left;

	final R right;

	Either(@Nullable L left, @Nullable R right) {
		if (left == null && right == null) {
			throw new IllegalArgumentException("Both left and right are null!");
		}
		this.left = left;
		this.right = right;
	}

	public static <L, R> Either<L, R> left(L left) {
		return new Either<>(left, null);
	}

	public static <L, R> Either<L, R> right(R right) {
		return new Either<>(null, right);
	}

	public <X, Y> Either<X, Y> bimap(Function<? super L, ? extends X> leftMapper,
			Function<R, Y> rightMapper) {
		if (isLeft()) {
			return new Either<>(leftMapper.apply(this.left), null);
		}
		return new Either<>(null, rightMapper.apply(this.right));
	}

	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Either<?, ?> either = (Either<?, ?>) o;
		return Objects.equals(left, either.left) && Objects.equals(right, either.right);
	}

	public <U> U fold(Function<? super L, ? extends U> leftMapper,
			Function<? super R, ? extends U> rightMapper) {
		if (isLeft()) {
			return leftMapper.apply(this.left);
		}
		return rightMapper.apply(this.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	public boolean isLeft() {
		return this.left != null;
	}

	public boolean isRight() {
		return this.right != null;
	}

	public Optional<L> left() {
		return Optional.ofNullable(this.left);
	}

	public <X> Either<X, R> leftMap(Function<? super L, ? extends X> leftMapper) {
		if (isLeft()) {
			return new Either<>(leftMapper.apply(this.left), null);
		}
		return new Either<>(null, this.right);
	}

	public L leftOrElseGet(Function<? super R, ? extends L> rightToLeft) {
		return this.left().orElseGet(() -> rightToLeft.apply(this.right));
	}

	/**
	 * @since 0.3.0
	 */
	public <X extends Throwable> L leftOrElseThrow(
			Function<? super R, ? extends X> exceptionSupplier) throws X {
		return this.left().orElseThrow(() -> exceptionSupplier.apply(this.right));
	}

	public Either<L, R> peekLeft(Consumer<? super L> action) {
		if (this.isLeft()) {
			action.accept(this.left);
		}
		return this;
	}

	public Optional<R> right() {
		return Optional.ofNullable(this.right);
	}

	public <Y> Either<L, Y> rightMap(Function<? super R, ? extends Y> rightMapper) {
		if (isRight()) {
			return new Either<>(null, rightMapper.apply(this.right));
		}
		return new Either<>(this.left, null);
	}

	public R rightOrElseGet(Function<? super L, ? extends R> leftToRight) {
		return this.right().orElseGet(() -> leftToRight.apply(this.left));
	}

	/**
	 * @since 0.3.0
	 */
	public <X extends Throwable> R rightOrElseThrow(
			Function<? super L, ? extends X> exceptionSupplier) throws X {
		return this.right().orElseThrow(() -> exceptionSupplier.apply(this.left));
	}

	public Either<L, R> peekRight(Consumer<? super R> action) {
		if (this.isRight()) {
			action.accept(this.right);
		}
		return this;
	}

	public Either<R, L> swap() {
		return new Either<>(this.right, this.left);
	}

	/**
	 * @since 0.6.0
	 */
	@SuppressWarnings("unchecked")
	public <U> Either<L, U> flatMap(Function<? super R, ? extends Either<L, U>> mapper) {
		if (isRight()) {
			return mapper.apply(this.right);
		}
		else {
			return (Either<L, U>) this;
		}
	}
}
