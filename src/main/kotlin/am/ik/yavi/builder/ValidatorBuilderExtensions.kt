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
package am.ik.yavi.builder

import am.ik.yavi.constraint.*
import am.ik.yavi.constraint.array.*
import am.ik.yavi.core.ConstraintCondition
import am.ik.yavi.core.ConstraintGroup
import am.ik.yavi.core.Validator
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KProperty1

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, N> ValidatorBuilder<T>.nest(prop: KProperty1<T, N?>, validator: Validator<N>): ValidatorBuilder<T> =
        this.nest(prop, prop.name, validator)

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, N> ValidatorBuilder<T>.nestIfPresent(prop: KProperty1<T, N?>, validator: Validator<N>): ValidatorBuilder<T> =
        this.nestIfPresent(prop, prop.name, validator)

@Deprecated("Deprecated in favour of new Kotlin DSL")
@Suppress("UNCHECKED_CAST")
fun <T, N> ValidatorBuilder<T>.nest(prop: KProperty1<T, N?>, block: ValidatorBuilder<N>.() -> Unit): ValidatorBuilder<T> =
        this.nest(prop, prop.name) { it.apply(block as ValidatorBuilder<N?>.() -> Unit) }

@Deprecated("Deprecated in favour of new Kotlin DSL")
@Suppress("UNCHECKED_CAST")
fun <T, N> ValidatorBuilder<T>.nestIfPresent(prop: KProperty1<T, N?>, block: ValidatorBuilder<N>.() -> Unit): ValidatorBuilder<T> =
        this.nestIfPresent(prop, prop.name) { it.apply(block as ValidatorBuilder<N?>.() -> Unit) }

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, L : Collection<E>?, E> ValidatorBuilder<T>.forEach(prop: KProperty1<T, L>, validator: Validator<E>): ValidatorBuilder<T> =
        this.forEach(prop, prop.name, validator)

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, L : Collection<E>?, E> ValidatorBuilder<T>.forEachIfPresent(prop: KProperty1<T, L?>, validator: Validator<E>): ValidatorBuilder<T> =
        this.forEachIfPresent(prop, prop.name, validator)

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, L : Collection<E>?, E> ValidatorBuilder<T>.forEach(prop: KProperty1<T, L?>, block: ValidatorBuilder<E>.() -> Unit): ValidatorBuilder<T> =
        this.forEach(prop, prop.name) { it.apply(block) }

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, L : Collection<E>?, E> ValidatorBuilder<T>.forEachIfPresent(prop: KProperty1<T, L?>, block: ValidatorBuilder<E>.() -> Unit): ValidatorBuilder<T> =
        this.forEachIfPresent(prop, prop.name) { it.apply(block) }

@JvmName("forEachMapMap")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, K, V> ValidatorBuilder<T>.forEach(prop: KProperty1<T, Map<K, V>?>, validator: Validator<V>): ValidatorBuilder<T> =
        this.forEach(prop, prop.name, validator)

@JvmName("forEachIfPresentMap")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, K, V> ValidatorBuilder<T>.forEachIfPresent(prop: KProperty1<T, Map<K, V>?>, validator: Validator<V>): ValidatorBuilder<T> =
        this.forEachIfPresent(prop, prop.name, validator)

@JvmName("forEachMapMap")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, K, V> ValidatorBuilder<T>.forEach(prop: KProperty1<T, Map<K, V>?>, block: ValidatorBuilder<V>.() -> Unit): ValidatorBuilder<T> =
        this.forEach(prop, prop.name) { it.apply(block) }

@JvmName("forEachIfPresentMap")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, K, V> ValidatorBuilder<T>.forEachIfPresent(prop: KProperty1<T, Map<K, V>?>, block: ValidatorBuilder<V>.() -> Unit): ValidatorBuilder<T> =
        this.forEachIfPresent(prop, prop.name) { it.apply(block) }

@JvmName("forEachMapArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, E> ValidatorBuilder<T>.forEach(prop: KProperty1<T, Array<E>?>, validator: Validator<E>): ValidatorBuilder<T> =
        this.forEach(prop, prop.name, validator)

@JvmName("forEachIfPresentArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, E> ValidatorBuilder<T>.forEachIfPresent(prop: KProperty1<T, Array<E>?>, validator: Validator<E>): ValidatorBuilder<T> =
        this.forEachIfPresent(prop, prop.name, validator)

@JvmName("forEachMapArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, E> ValidatorBuilder<T>.forEach(prop: KProperty1<T, Array<E>?>, block: ValidatorBuilder<E>.() -> Unit): ValidatorBuilder<T> =
        this.forEach(prop, prop.name) { it.apply(block) }

@JvmName("forEachIfPresentArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, E> ValidatorBuilder<T>.forEachIfPresent(prop: KProperty1<T, Array<E>?>, block: ValidatorBuilder<E>.() -> Unit): ValidatorBuilder<T> =
        this.forEachIfPresent(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnCharSequence")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, String?>, block: CharSequenceConstraint<T, String?>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnBoolean")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Boolean?>, block: BooleanConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnChar")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Char?>, block: CharacterConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnByte")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Byte?>, block: ByteConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnShort")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Short?>, block: ShortConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnInt")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Int?>, block: IntegerConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnLong")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Long?>, block: LongConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnFloat")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Float?>, block: FloatConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnDouble")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Double?>, block: DoubleConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnBigInteger")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, BigInteger?>, block: BigIntegerConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnBigDecimal")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, BigDecimal?>, block: BigDecimalConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnCollection")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, L : Collection<E>?, E> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, L?>, block: CollectionConstraint<T, L?, E>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnMap")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, K, V> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Map<K, V>?>, block: MapConstraint<T, K, V>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnObjectArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, E> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, Array<E>?>, block: ObjectArrayConstraint<T, E>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnBooleanArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, BooleanArray?>, block: BooleanArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnCharArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, CharArray?>, block: CharArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnByteArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, ByteArray?>, block: ByteArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnShortArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, ShortArray?>, block: ShortArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnIntArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, IntArray?>, block: IntArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnLongArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, LongArray?>, block: LongArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnFloatArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, FloatArray?>, block: FloatArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@JvmName("konstraintOnDoubleArray")
@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraint(prop: KProperty1<T, DoubleArray?>, block: DoubleArrayConstraint<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraint(prop, prop.name) { it.apply(block) }

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T, E> ValidatorBuilder<T>.konstraintOnObject(prop: KProperty1<T, E?>, block: ObjectConstraint<T, E?>.() -> Unit): ValidatorBuilder<T> =
        this.constraintOnObject(prop, prop.name) { it.apply(block) }

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraintOnCondition(condition: ConstraintCondition<T>, block: ValidatorBuilder<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraintOnCondition(condition, ValidatorBuilder.ValidatorBuilderConverter { it.apply(block) })

@Deprecated("Deprecated in favour of new Kotlin DSL")
fun <T> ValidatorBuilder<T>.konstraintOnGroup(group: ConstraintGroup, block: ValidatorBuilder<T>.() -> Unit): ValidatorBuilder<T> =
        this.constraintOnCondition(group.toCondition(), ValidatorBuilder.ValidatorBuilderConverter { it.apply(block) })
