package com.example.dicoding.myunittesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by dicoding on 1/26/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    private MainPresenter presenter;
    private MainView view;

    /*
    Inisiasi kelas
    Mocking digunakan untuk membuat object tiruan
     */
    @Before
    public void setUp() throws Exception {
        view = mock(MainView.class);
        presenter = new MainPresenter(view);
    }

    @Test
    public void testVolumeWithIntegerInput() throws Exception {
        double volume = presenter.volume(2, 8, 1);
        assertEquals(16, volume, 0.0001);
    }
    @Test
    public void testVolumeWithDoubleInput() throws Exception {
        double volume = presenter.volume(2.3, 8.1, 2.9);
        assertEquals(54.026999999999994, volume, 0.0001);
    }

    @Test
    public void testVolumeWithZeroInput() throws Exception {
        double volume = presenter.volume(0, 0, 0);
        assertEquals(0.0, volume , 0.0001);
    }
    @Test
    public void testHitungVolume() throws Exception {
        presenter.hitungVolume(11.1, 2.2, 1);
        verify(view).tampilVolume(any(MainModel.class));
    }

}
