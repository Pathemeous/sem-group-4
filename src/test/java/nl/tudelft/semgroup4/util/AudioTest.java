//package nl.tudelft.semgroup4.util;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import nl.tudelft.semgroup4.resources.Resources;
//import nl.tudelft.semgroup4.util.Audio;
//
//import org.junit.Test;
//
//public class AudioTest {
//    
//    @Test
//    public final void testPlayTimeUp() {
//        Audio.playTimeUp();
//        assertTrue(Resources.timeUp.playing());
//    }
//    
//    @Test
//    public final void testPlayTitleScreen() {
//        Audio.playTitleScreen();
//        assertTrue(Resources.titleScreenMusic.playing());
//    }
//    
//    @Test
//    public final void testStopTitleScreen() {
//        Audio.stopTitleScreen();
//        assertFalse(Resources.titleScreenMusic.playing());
//    }
//    
//    @Test
//    public final void testPlayDeath() {
//        Audio.playDeath();
//        assertTrue(Resources.death.playing());        
//    }
//}
