package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TripServiceTest {

  private static final User GUEST = null;
  private static final User DUMMY_USER = new User();
  private static final User REGISTERED_USER = new User();

  private TripService tripService = new TestTripService();

  private User currentlyLoggedInUser;

  @Test(expected = UserNotLoggedInException.class)
  public void shouldThrowExceptionIfUserIsNotLoggedIn() {
    // Given
    currentlyLoggedInUser = GUEST;

    // When
    tripService.getTripsByUser(DUMMY_USER);

    // Then
    // exception is thrown

  }

  @Test
  public void shouldReturnEmptyListWhenUserDontHaveFriends() {
    // Given
    User userWithoutFriends = new User();
    currentlyLoggedInUser = REGISTERED_USER;

    // When
    List<Trip> trips = tripService.getTripsByUser(userWithoutFriends);

    // Then
    assertThat(trips)
        .isEmpty();

  }

  @Test
  public void shouldReturnEmptyListWhenCurrentUserIsNotFiendWithUser() {
    // Given
    User userWithFriends = new User();
    userWithFriends.addFriend(new User());
    userWithFriends.addFriend(new User());

    currentlyLoggedInUser = REGISTERED_USER;

    // When
    List<Trip> trips = tripService.getTripsByUser(userWithFriends);

    // Then
    assertThat(trips)
        .isEmpty();

  }

  // TODO: Remove this
  private class TestTripService extends TripService {

    @Override
    protected User getLoggedUser() {
      return currentlyLoggedInUser;
    }

  }
}
