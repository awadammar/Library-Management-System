package cc.maids.library.management;

import cc.maids.library.management.controller.PatronController;
import cc.maids.library.management.entity.Patron;
import cc.maids.library.management.service.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(Patron.class)
class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @Test
    void testGetAllPatrons() {
        // Arrange
        List<Patron> patrons = Arrays.asList(
                new Patron(1L, "Ammar Awad"),
                new Patron(2L, "Ahmed Ali")
        );
        when(patronService.getAllPatrons()).thenReturn(patrons);

        // Act
        List<Patron> result = patronController.getAllPatrons();

        // Assert
        assertEquals(2, result.size());
        assertEquals(patrons, result);
    }

    @Test
    void testGetPatronById() {
        // Arrange
        Long patronId = 1L;
        Patron patron = new Patron(patronId, "Ammar Awad");
        when(patronService.getPatronById(patronId)).thenReturn(patron);

        // Act
        ResponseEntity<Patron> response = patronController.getPatronById(patronId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
     void testAddPatron() {
        // Arrange
        Patron patronToAdd = new Patron();
        patronToAdd.setName("Ammar Awad");
        when(patronService.addPatron(any(Patron.class))).thenReturn(patronToAdd);

        // Act
        ResponseEntity<Patron> response = patronController.addPatron(patronToAdd);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patronToAdd, response.getBody());
    }

    @Test
     void testUpdatePatron() {
        // Arrange
        Long patronId = 1L;
        Patron updatedPatron = new Patron(patronId, "Updated Name");
        when(patronService.updatePatron(eq(patronId), any(Patron.class))).thenReturn(updatedPatron);

        // Act
        ResponseEntity<Patron> response = patronController.updatePatron(patronId, updatedPatron);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPatron, response.getBody());
    }

    @Test
     void testDeletePatron() {
        // Arrange
        Long patronId = 1L;
        doNothing().when(patronService).deletePatron(patronId);

        // Act
        ResponseEntity<Void> response = patronController.deletePatron(patronId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
