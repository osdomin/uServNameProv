package com.serenity.alm.nameprovider;
/*
 * 
 * (C) Copyright 2015 
 * mailTo: TBD
 * 
 * Licensed under the Apache License, Version 2.0 - the "License";
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     artudf
 */


import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NameProviderTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testName() {
		String body = this.restTemplate.getForObject("/name", String.class);
		assertThat(body).isEqualTo("Serenity ALM");
	}

	@Test
	public void testLastName() {
		String body = this.restTemplate.getForObject("/lastName", String.class);
		assertThat(body).isEqualTo("Team");
	}

	@After
	public void tearDown() throws Exception {

	}
}
