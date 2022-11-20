package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 * FeatureTest <p/>
 *
 * @author HeQiang
 * @since 2022/11/20
 **/
@ExtendWith(OutputCaptureExtension.class)
class FeatureTest {

	/**
	 * 运行期增加：-XX:+ShowCodeDetailsInExceptionMessages
	 */
	@Test
	void nullPointDetailTest(CapturedOutput output) {
		try {
			Child child = new Child();
			child.name = "Hery";
			Parent parent = new Parent();
			GrandFather grandFather = new GrandFather();
			grandFather.parent = parent;
			System.out.println(grandFather.parent.child.name);
		} catch (Throwable e) {
			// ignore
			System.err.println(e.getMessage());
		}
		assertThat(output.getErr()).contains("\"grandFather.parent.child\" is null");
	}

	@Test
	void switchTest() {
		String day = "TH";
		String result = switch (day) {
			case "M", "W", "F" -> "MWF";
			case "T", "TH", "S" -> "TTS";
			default -> {
				if(day.isEmpty())
					yield "Please insert a valid day.";
				else
					yield "Looks like a Sunday.";
			}

		};
		Assertions.assertEquals("TTS", result);
	}
}

class Child {

	public String name;
}

class Parent {

	public Child child;
}

class GrandFather {

	public Parent parent;
}