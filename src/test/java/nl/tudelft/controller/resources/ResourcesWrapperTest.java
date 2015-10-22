package nl.tudelft.controller.resources;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class ResourcesWrapperTest {

    private HashMap mockedMapSound;
    private HashMap mockedMap;
    private ResourcesWrapper wrapper;
    private String fileSeparator;
    private Sound mockedSound;

    /**
     * Create the needed mocks.
     */
    @Before
    public void setUp() {
        wrapper = new ResourcesWrapper();
        mockedMap = mock(HashMap.class);
        mockedMapSound = mock(HashMap.class);
        mockedSound = mock(Sound.class);
        wrapper.setMaps(mockedMapSound, mockedMap);
        fileSeparator = System.getProperty("file.separator");
        when(mockedMapSound.get(any())).thenReturn(mockedSound);
    }

    @Test
    public void testPlayFireSound1() {
        wrapper.setMusicOn(false);
        wrapper.playFireSound();
        verify(mockedMapSound, never()).get("sound" + fileSeparator + "weaponFire.ogg");
        verify(mockedSound, never()).loop();
    }

    @Test
    public void testPlayFireSound2() {
        wrapper.setMusicOn(true);
        wrapper.playFireSound();
        verify(mockedMapSound, times(1)).get("sound" + fileSeparator + "weaponFire.ogg");
        verify(mockedSound, times(1)).loop();
    }

    @Test
    public void testStopFireSound() {
        wrapper.stopFireSound();
        verify(mockedMapSound, times(1)).get("sound" + fileSeparator + "weaponFire.ogg");
        verify(mockedSound, times(1)).stop();
    }

    @Test
    public void testPlayBubbleSplit1() {
        wrapper.setMusicOn(false);
        wrapper.playBubbleSplit();
        verify(mockedMapSound, never()).get("sound" + fileSeparator + "pop.ogg");
        verify(mockedSound, never()).play();
    }

    @Test
    public void testPlayBubbleSplit2() {
        wrapper.setMusicOn(true);
        wrapper.playBubbleSplit();
        verify(mockedMapSound, times(1)).get("sound" + fileSeparator + "pop.ogg");
        verify(mockedSound, times(1)).play();
    }

    @Test
    public void testPlayTimeUp1() {
        wrapper.setMusicOn(false);
        wrapper.playTimeUp();
        verify(mockedMapSound, never()).get("sound" + fileSeparator + "timeUp.ogg");
        verify(mockedSound, never()).play();
    }

    @Test
    public void testPlayTimeUp2() {
        wrapper.setMusicOn(true);
        wrapper.playTimeUp();
        verify(mockedMapSound, times(1)).get("sound" + fileSeparator + "timeUp.ogg");
        verify(mockedSound, times(1)).play();
    }

    @Test
    public void testPlayTitleScreen1() {
        Music mockedMusic = mock(Music.class);
        when(mockedMapSound.get(any())).thenReturn(mockedMusic);
        wrapper.setMusicOn(false);
        wrapper.playTitleScreen();
        verify(mockedMapSound, never()).get("music" + fileSeparator + "titleScreen.ogg");
        verify(mockedMusic, never()).play();
    }

    @Test
    public void testPlayTitleScreen2() {
        Music mockedMusic = mock(Music.class);
        when(mockedMapSound.get(any())).thenReturn(mockedMusic);
        wrapper.setMusicOn(true);
        wrapper.playTitleScreen();
        verify(mockedMapSound, times(1)).get("music" + fileSeparator + "titleScreen.ogg");
        verify(mockedMusic, times(1)).play();
    }

    @Test
    public void testStopTitleScreen() {
        Music mockedMusic = mock(Music.class);
        when(mockedMapSound.get(any())).thenReturn(mockedMusic);
        wrapper.stopTitleScreen();
        verify(mockedMapSound, times(1)).get("music" + fileSeparator + "titleScreen.ogg");
        verify(mockedMusic, times(1)).stop();
    }

    @Test
    public void testPlayDeath1() {
        wrapper.setMusicOn(false);
        wrapper.playDeath();
        verify(mockedMapSound, never()).get("sound" + fileSeparator + "death.ogg");
        verify(mockedSound, never()).play();
    }

    @Test
    public void testPlayDeath2() {
        wrapper.setMusicOn(true);
        wrapper.playDeath();
        verify(mockedMapSound, times(1)).get("sound" + fileSeparator + "death.ogg");
        verify(mockedSound, times(1)).play();
    }

    @Test
    public void testGetWallImage() {
        wrapper.getWallImage();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "wall2_h.png");
    }

    @Test
    public void testGeVtWallImage() {
        wrapper.getVwallImage();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "wall2_v.png");
    }

    @Test
    public void testGetSmallHWallImage() {
        wrapper.getSmallHWallImage();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "small_wall_h.png");
    }

    @Test
    public void testGetSmallVWallImage() {
        wrapper.getSmallVWallImage();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "small_wall_v.png");
    }

    @Test
    public void testGetWeaponImageRegular() {
        wrapper.getWeaponImageRegular();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "weapon_arrow.png");
    }

    @Test
    public void testGetWeaponImageSticky() {
        wrapper.getWeaponImageSticky();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "weapon_arrow.png");
    }

    @Test
    public void testGetWeaponFlower() {
        wrapper.getWeaponImageFlower();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "weapon_flowers.png");
    }

    @Test
    public void testGetTitleScreenBackground() {
        wrapper.getTitleScreenBackground();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "titlescreen2.png");
    }

    @Test
    public void testGetBackgroundImage() {
        wrapper.getBackgroundImage();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "level1.jpg");
    }

    @Test
    public void testGetDashboardPlayerContainerLeft() {
        wrapper.getDashboardPlayerContainerLeft();
        verify(mockedMap, times(1)).get("img" + fileSeparator
                + "dashboard" + fileSeparator + "player_container_1.png");
    }

    @Test
    public void testGetDashboardPlayerContainerRight() {
        wrapper.getDashboardPlayerContainerRight();
        verify(mockedMap, times(1)).get("img" + fileSeparator
                + "dashboard" + fileSeparator + "player_container_2.png");
    }

    @Test
    public void testGetDashboardLivesContainer() {
        wrapper.getDashboardLivesContainer();
        verify(mockedMap, times(1)).get("img" + fileSeparator
                + "dashboard" + fileSeparator + "lives_container.png");
    }

    @Test
    public void testGetDashboardlivesFull() {
        wrapper.getDashboardlivesFull();
        verify(mockedMap, times(1)).get("img" + fileSeparator
                + "dashboard" + fileSeparator + "lives_full.png");
    }

    @Test
    public void testGetDashboardlivesEmpty() {
        wrapper.getDashboardlivesEmpty();
        verify(mockedMap, times(1)).get("img" + fileSeparator
                + "dashboard" + fileSeparator + "lives_empty.png");
    }

    @Test
    public void testGetLevelContainer() {
        wrapper.getLevelContainer();
        verify(mockedMap, times(1)).get("img" + fileSeparator
                + "dashboard" + fileSeparator + "level_container.png");
    }

    @Test
    public void testGetPlayerImageStill() {
        wrapper.getPlayerImageStill();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "player_still.png");
    }

    @Test
    public void testGetOn() {
        wrapper.getOn();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "on.png");
    }

    @Test
    public void testGetOff() {
        wrapper.getOff();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "off.png");
    }

    @Test
    public void testGetSoundText() {
        wrapper.getSoundText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "sound.png");
    }

    @Test
    public void testGetOptionsText() {
        wrapper.getOptionsText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "options.png");
    }

    @Test
    public void testGetBackText() {
        wrapper.getBackText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "back.png");
    }

    @Test
    public void testGetQuitText() {
        wrapper.getQuitText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "quitText.png");
    }

    @Test
    public void testGetPauseText() {
        wrapper.getPauseText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "pausedText.png");
    }

    @Test
    public void testGetNewKeyText() {
        wrapper.getNewKeyText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "newKeyText.png");
    }

    @Test
    public void testGetShopBackGround() {
        wrapper.getShopBackGround();

        verify(mockedMap, times(1)).get("img" + fileSeparator + "shopBackground.png");
    }

    @Test
    public void testGetPlayerWalkLeft() {
        wrapper.getPlayerWalkLeft();
        verify(mockedMap, times(1))
                .get("animation" + fileSeparator + "player" + fileSeparator + "pl");
    }

    @Test
    public void testGetPlayerWalkRight() {
        wrapper.getPlayerWalkRight();
        verify(mockedMap, times(1))
                .get("animation" + fileSeparator + "player" + fileSeparator + "pr");
    }

    @Test
    public void testGetBubbleImage1() {
        wrapper.getBubbleImage1();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "yball1.png");
    }

    @Test
    public void testGetBubbleImage2() {
        wrapper.getBubbleImage2();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "yball2.png");
    }

    @Test
    public void testGetBubbleImage3() {
        wrapper.getBubbleImage1();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "yball3.png");
    }

    @Test
    public void testGetBubbleImage4() {
        wrapper.getBubbleImage4();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "rball4.png");
    }

    @Test
    public void testGetBubbleImage5() {
        wrapper.getBubbleImage5();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "rball5.png");
    }

    @Test
    public void testGetBubbleImage6() {
        wrapper.getBubbleImage6();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "rball6.png");
    }

    @Test
    public void testGetPowerShield() {
        wrapper.getPowerShield();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "powerup_shield.png");
    }

    @Test
    public void testGetContinueText() {
        wrapper.getContinueText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "continue.png");
    }

    @Test
    public void testGetLoggerText() {
        wrapper.getLoggerText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "logger.png");
    }

    @Test
    public void testGetKeyText() {
        wrapper.getKeyText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "keyBindings.png");
    }

    @Test
    public void testGetShopText() {
        wrapper.getShopText();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "shop.png");
    }

    @Test
    public void testGetSpecialWeapon() {
        wrapper.getSpecialWeapon();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "pickup_weapon_special.png");
    }

    @Test
    public void testGetBuy() {
        wrapper.getBuy();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "buy.png");
    }

    @Test
    public void testGetPlayer2On() {
        wrapper.getPlayer2On();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "player2TextOn.png");
    }

    @Test
    public void testGetPlayer2Off() {
        wrapper.getPlayer2Off();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "player2TextOff.png");
    }

    @Test
    public void testGetPlayer1On() {
        wrapper.getPlayer1On();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "player1TextOn.png");
    }

    @Test
    public void testGetPlayer1Off() {
        wrapper.getPlayer1Off();
        verify(mockedMap, times(1)).get("img" + fileSeparator + "player1TextOff.png");
    }

    @Test
    public void testIsMusicOn() {
        wrapper.setMusicOn(false);
        assertFalse(wrapper.isMusicOn());
        wrapper.setMusicOn(true);
        assertTrue(wrapper.isMusicOn());
    }
}
