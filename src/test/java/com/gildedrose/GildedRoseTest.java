package com.gildedrose;

import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    void normalItemsWithPositiveSellinDecreaseQualityByOne() {
        Item item = new Item("any normal item", 1, 100);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(99, subject.items[0].quality);
    }

    @Test
    void normalItemsWithZeroSellinDecreaseQualityByTwo() {
        Item item = new Item("any normal item", 0, 100);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(98, subject.items[0].quality);
    }

    @Test
    void normalItemsWithNegativeSellinDecreaseQualityByTwo() {
        Item item = new Item("any normal item", 0, 100);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(98, subject.items[0].quality);
    }

    @Test
    void agedBrieGoesUpInQualityWhenBelow50AndPositiveSellin() {
        Item item = new Item("Aged Brie", 1, 49);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(50, subject.items[0].quality);
    }

    @Test
    void agedBrieAbove50QualityDoesntChangeQualityWithPostiveSellin() {
        Item item = new Item("Aged Brie", 1, 51);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(51, subject.items[0].quality);
    }

    @Test
    void agedBrieGainsQualityBy2WithNegativeSellinWhenLessThan50Quality() {
        Item item = new Item("Aged Brie", -1, 46);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(48, subject.items[0].quality);
    }

    @Test
    void backstagePassesWithQualityBellow50AndSellinBellow11IncreaseQualityBy2() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40);
        GildedRose subject = new GildedRose(new Item[] {item});
        subject.updateQuality();
        assertEquals(42, subject.items[0].quality);
    }

    @Test
    void backstagePassesWithQualityBellow50AndSellinBellow6IncreaseQualityBy3() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        GildedRose subject = new GildedRose(new Item[] {item});
        subject.updateQuality();
        assertEquals(43, subject.items[0].quality);
    }

    @Test
    void backstagePassesWithNegativeSellinLoseAllQuality() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 40);
        GildedRose subject = new GildedRose(new Item[] {item});
        subject.updateQuality();
        assertEquals(0, subject.items[0].quality);
    }

    @Test
    void AllItemsNotSulfurasLoseOneSellin() {
        Item normalItem = new Item("any normal item", 2, 100);
        Item passesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 2, 40);
        Item brieItem = new Item("Aged Brie", 2, 49);
        GildedRose subject = new GildedRose(new Item[] {normalItem, passesItem, brieItem});
        subject.updateQuality();

        assertTrue(Arrays.stream(subject.items).allMatch(i -> i.sellIn == 1));
    }

    @Test
    void SulfurasDoesNotLoseSellinOrQuality() {
        Item sulfarus = new Item("Sulfuras, Hand of Ragnaros", 2, 100);
         new Item("Aged Brie", 2, 49);
        GildedRose subject = new GildedRose(new Item[] {sulfarus});
        subject.updateQuality();

        assertEquals(subject.items[0].sellIn, 2);
        assertEquals(subject.items[0].quality, 100);
    }

}
