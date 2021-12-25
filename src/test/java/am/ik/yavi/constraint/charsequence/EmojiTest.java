/*
 * Copyright (C) 2018-2021 Toshiaki Maki <makingx@gmail.com>
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
package am.ik.yavi.constraint.charsequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmojiTest {

	@Test
	public void elf() {
		String emoji = "🧝🧝🏻🧝🏼🧝🏽🧝🏾🧝🏿";
		assertThat(emoji).hasSize(22);
		assertThat(Emoji.bestEffortCount(emoji)).isEqualTo(6);
	}

	@Test
	public void emoji() {
		String emoji = "I am 👱🏿";
		assertThat(emoji).hasSize(9);
		assertThat(Emoji.bestEffortCount(emoji)).isEqualTo(6);
	}

	@Test
	public void emoji11All() throws Exception {
		verifyEmojiAll("emoji-test-11.txt");
	}

	@Test
	public void emoji12All() throws Exception {
		verifyEmojiAll("emoji-test-12.txt");
	}

	@Test
	public void family() {
		String emoji = "👩‍❤️‍💋‍👩👪👩‍👩‍👧‍👦👨‍👦‍👦👨‍👧👩‍👧";
		assertThat(emoji).hasSize(42);
		assertThat(Emoji.bestEffortCount(emoji)).isEqualTo(6);
	}

	@Test
	public void heart() {
		String emoji = "❤️💙💚💛🧡💜🖤";
		assertThat(emoji).hasSize(14);
		assertThat(Emoji.bestEffortCount(emoji)).isEqualTo(7);
	}

	@Test
	public void subdivisionFlags() {
		String emoji = "🏴󠁧󠁢󠁥󠁮󠁧󠁿🏴󠁧󠁢󠁳󠁣󠁴󠁿🏴󠁧󠁢󠁷󠁬󠁳󠁿";
		assertThat(emoji).hasSize(42);
		assertThat(Emoji.bestEffortCount(emoji)).isEqualTo(3);
	}

	void verifyEmojiAll(String file) throws Exception {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				this.getClass().getClassLoader().getResourceAsStream(file)))) {
			String line;
			int n = 0;
			do {
				n++;
				line = reader.readLine();
				if (line == null || line.startsWith("#") || line.isEmpty()) {
					continue;
				}
				int[] codePoints = Arrays.stream(line.split(";")[0].trim().split(" "))
						.mapToInt(x -> Integer.parseInt(x, 16)).toArray();
				String emoji = new String(codePoints, 0, codePoints.length);
				int len = Emoji.bestEffortCount("This is " + emoji + ".");
				assertThat(len).describedAs(emoji + " L" + n).isEqualTo(10);

			}
			while (line != null);
		}
	}
}
