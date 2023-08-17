package com.observation.observation;

import com.observation.observation.entity.Observation;
import com.observation.observation.repository.ObservationRepository;
import com.observation.observation.service.ObservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ObservationApplicationTests {

	@Mock
	ObservationRepository observationRepository;
	@InjectMocks
	ObservationService observationService;




	@Test void shouldAddNewObservationWhenValidDataProvided() {
		LocalDate date = LocalDate.parse("2002-06-25");
		Observation observation = new Observation(1,1,date,"oki theo", true);
		Mockito.when(observationRepository.save(observation)).thenReturn(observation);


		Observation result = observationService.createObservation(1,1,"oki theo", true,date);


		Assertions.assertEquals(observation, result);
	}
	@Test void shouldSelectObservationsCorrespondingUserId() {

		LocalDate date = LocalDate.parse("2002-06-25");
		Observation observation = new Observation(1,1,date,"oki theo", true);
		Observation observation2 = new Observation(1,1,date,"oki theo", true);
		Observation observation3 = new Observation(2,1,date,"oki theo", true);
		Observation observation4 = new Observation(1,1,date,"oki theo", true);
		Mockito.when(observationRepository.save(observation)).thenReturn(observation);
		Mockito.when(observationRepository.save(observation2)).thenReturn(observation2);
		Mockito.when(observationRepository.save(observation3)).thenReturn(observation3);
		Mockito.when(observationRepository.save(observation4)).thenReturn(observation4);



		List<Observation> result = observationService.getByUserId(1);


		Assertions.assertEquals(3, result.size());
	}


}
