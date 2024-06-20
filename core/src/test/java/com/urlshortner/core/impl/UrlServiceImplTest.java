package com.urlshortner.core.impl;

import com.urlshortner.core.domain.CreateUrlEntry;
import com.urlshortner.core.domain.UrlEntry;
import com.urlshortner.core.exceptions.NotFoundException;
import com.urlshortner.core.repositories.UrlEntryRepo;
import com.urlshortner.core.service.CounterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UrlServiceImplTest {

    @Mock
    UrlEntryRepo mockUrlEntryRepo;
    // UrlEntryRepo dummyRepo = new DummyUrlEntryRepo();

    @Mock
    CounterService mockCounterService;
    // CounterService dummyService = new DummyCounterService();

    @InjectMocks
    UrlServiceImpl urlService;
    // UrlService urlService = new UrlServiceImpl(dummyRepo, dummyService);

    @Test
    public void shouldCreateUrlEntry() {
        // setup
        String longUrl="www.google.com";
        Mockito.when(mockCounterService.getCount()).thenReturn(1L);  // getCount() of DummyCounterService
        Mockito.when(mockUrlEntryRepo.createUrl(Mockito.any())).thenReturn(new UrlEntry(longUrl, "000001")); // createUrl() of DummyUrlEntryRepo

        // Test
        UrlEntry urlEntry = urlService.create(new CreateUrlEntry(longUrl));

        // verify results
        String actualId = urlEntry.getId();
        String expectedId = "000001";
        String actualLongUrl = urlEntry.getLongUrl();
        String expectedLongUrl = longUrl;

        Assertions.assertEquals(expectedId, actualId);    // if(expectedId.equals(actualId)) s.o.p("pass")
        Assertions.assertEquals(expectedLongUrl, actualLongUrl);  // if(expectedLongUrl.equals(actualLongUrl)) s.o.p("pass")
    }

    @Test
    public void shouldGetLongUrl() throws NotFoundException {
        String id = "000001";
        Mockito.when(mockUrlEntryRepo.existsById(id)).thenReturn(true);
        Mockito.when(mockUrlEntryRepo.findById(id)).thenReturn(new UrlEntry("www.google.com", id));

        String actualLongUrl = urlService.getLongUrl(id);

        String expectedLongUrl = "www.google.com";
        Assertions.assertEquals(expectedLongUrl, actualLongUrl);
    }

    @Test
    public void shouldGetExceptionWhenIdDoesNotExist() {
        String id = "000003";
        Mockito.when(mockUrlEntryRepo.existsById(id)).thenReturn(false);

        Assertions.assertThrowsExactly(NotFoundException.class, () -> urlService.getLongUrl(id));
    }

    @Test
    public void shouldDeleteKey(){
        String id = "000004";
        urlService.delete(id);
        Mockito.verify(mockUrlEntryRepo).deleteUrl(id);
    }
}
