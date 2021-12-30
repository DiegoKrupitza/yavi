package am.ik.yavi.constraint.temporal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocalTimeConstraintTest {

	private static final LocalTime BASE_TIME = LocalTime.of(12, 30);

	@Test
	void isBeforeValid() {
		LocalTime future = BASE_TIME.plusHours(10);
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.before(future));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void isBeforeInValid() {
		LocalTime past = BASE_TIME.minusHours(10);
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.before(past));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void isBeforeSupplierValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.before(() -> BASE_TIME.plusHours(10)));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void isBeforeSupplierInValid() {
		LocalTime past = BASE_TIME.minusHours(10);
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.before(() -> past));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void isAfterInValid() {
		LocalTime future = BASE_TIME.plusHours(10);
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.after(future));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void isAfterValid() {
		LocalTime past = BASE_TIME.minusHours(10);
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.after(past));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void isAfterSupplierInValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.after(() -> BASE_TIME.plusHours(10)));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void isAfterSupplierValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.after(() -> BASE_TIME.minusHours(10)));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@ParameterizedTest
	@MethodSource("validBetweenDates")
	void isBetweenValid(LocalTime BASE_TIME, LocalTime rangeFrom, LocalTime rangeTo) {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.between(rangeFrom, rangeTo));
		assertThat(predicate.test(LocalTimeConstraintTest.BASE_TIME)).isTrue();
	}

	@Test
	void isBetweenExactInValid() {

		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.between(BASE_TIME, BASE_TIME));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void isBetweenInValidException() {
		LocalTime rangeTo = BASE_TIME.minusHours(1);
		LocalTime rangeFrom = BASE_TIME.plusHours(1);

		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.between(rangeFrom, rangeTo));
		assertThatThrownBy(() -> predicate.test(BASE_TIME))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Parameter 'rangeFrom' has to be before 'rangeTo'");
	}

	@ParameterizedTest
	@MethodSource("validBetweenDates")
	void isBetweenSupplierValid(LocalTime now, LocalTime rangeFrom, LocalTime rangeTo) {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.between(() -> rangeFrom, () -> rangeTo));
		assertThat(predicate.test(now)).isTrue();
	}

	@Test
	void isBetweenSupplierExactInValid() {
		Supplier<LocalTime> nowSupplier = () -> BASE_TIME;

		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.between(nowSupplier, nowSupplier));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void isBetweenSupplierInValidException() {
		Predicate<LocalTime> predicate = retrievePredicate(c -> c
				.between(() -> BASE_TIME.plusHours(1), () -> BASE_TIME.minusHours(1)));
		assertThatThrownBy(() -> predicate.test(BASE_TIME))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Parameter 'rangeFrom' has to be before 'rangeTo'");
	}

	@Test
	void hourIntegerValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.hour(BASE_TIME.getHour()));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void hourLocalTimeValid() {
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.hour(BASE_TIME));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void hourIntegerInValid() {
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.hour(1));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void hourIntegerInValidException() {
		assertThatThrownBy(() -> retrievePredicate(c -> c.hour(-1)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("hour");

		assertThatThrownBy(() -> retrievePredicate(c -> c.hour(24)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("hour");
	}

	@Test
	void hourLocalTimeInValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.hour(LocalTime.of(1, 0)));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void minuteIntegerValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.minute(BASE_TIME.getMinute()));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void minuteLocalTimeValid() {
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.minute(BASE_TIME));
		assertThat(predicate.test(BASE_TIME)).isTrue();
	}

	@Test
	void minuteIntegerInValid() {
		Predicate<LocalTime> predicate = retrievePredicate(c -> c.minute(1));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	@Test
	void minuteIntegerInValidException() {
		assertThatThrownBy(() -> retrievePredicate(c -> c.minute(-1)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("minute");

		assertThatThrownBy(() -> retrievePredicate(c -> c.minute(60)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("minute");
	}

	@Test
	void minuteLocalTimeInValid() {
		Predicate<LocalTime> predicate = retrievePredicate(
				c -> c.minute(LocalTime.of(12, 1)));
		assertThat(predicate.test(BASE_TIME)).isFalse();
	}

	private static Stream<Arguments> validBetweenDates() {
		return IntStream.rangeClosed(1, 10).boxed().map(i -> Arguments.of(BASE_TIME,
				BASE_TIME.minusHours(i), BASE_TIME.plusHours(i)));
	}

	private static Predicate<LocalTime> retrievePredicate(
			Function<LocalTimeConstraint<LocalTime>, LocalTimeConstraint<LocalTime>> constraint) {
		return constraint.apply(new LocalTimeConstraint<>()).predicates().peekFirst()
				.predicate();
	}

}
