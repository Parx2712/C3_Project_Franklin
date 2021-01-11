import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    // >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
        throws restaurantNotFoundException {

        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        assertEquals(restaurant, service.findRestaurantByName("Amelie's cafe"));
    }

    // You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and
    // Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception()
        throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class, () -> {
            service.findRestaurantByName("Amelie's cafe");
        });
    }
    // <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>



    // >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1()
        throws restaurantNotFoundException {

        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getNumberOfRestaurants();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getNumberOfRestaurants());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception()
        throws restaurantNotFoundException {
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getNumberOfRestaurants();
        service.addRestaurant("Pumpkin Tales", "Chennai", openingTime, closingTime);
        assertEquals(initialNumberOfRestaurants + 1, service.getNumberOfRestaurants());
    }
    // <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}