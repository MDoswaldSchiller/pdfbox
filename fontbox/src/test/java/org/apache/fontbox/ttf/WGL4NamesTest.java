package org.apache.fontbox.ttf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class WGL4NamesTest
{

    @Test
    void testAllNames()
    {
        String[] allNames = WGL4Names.getAllNames();
        assertNotNull(allNames);
        assertEquals(WGL4Names.NUMBER_OF_MAC_GLYPHS, allNames.length);
    }

    @Test
    void testGetGlyphName()
    {
        assertEquals(".notdef", WGL4Names.getGlyphName(0));
        assertEquals("equal", WGL4Names.getGlyphName(32));
        assertEquals("h", WGL4Names.getGlyphName(75));
        assertEquals("Aacute", WGL4Names.getGlyphName(201));
        assertEquals("Ocircumflex", WGL4Names.getGlyphName(209));
        assertEquals("ccaron", WGL4Names.getGlyphName(256));
        assertNull(WGL4Names.getGlyphName(WGL4Names.NUMBER_OF_MAC_GLYPHS + 1));
    }

    @Test
    void testGlyphIndices()
    {
        assertEquals(0, WGL4Names.getGlyphIndex(".notdef"));
        assertEquals(32, WGL4Names.getGlyphIndex("equal"));
        assertEquals(75, WGL4Names.getGlyphIndex("h"));
        assertEquals(201, WGL4Names.getGlyphIndex("Aacute"));
        assertEquals(209, WGL4Names.getGlyphIndex("Ocircumflex"));
        assertEquals(256, WGL4Names.getGlyphIndex("ccaron"));
        assertNull(WGL4Names.getGlyphIndex("INVALID"));
    }
}
