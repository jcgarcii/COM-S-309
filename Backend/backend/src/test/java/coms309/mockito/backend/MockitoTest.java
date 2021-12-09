package coms309.mockito.backend;

import static org.mockito.Mockito.*;

import coms309.roundtrip.backend.model.FriendRequest;
import coms309.roundtrip.backend.model.Friendship;
import coms309.roundtrip.backend.model.User;
import coms309.roundtrip.backend.model.SavedDrawing;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockitoTest {

    User toUser = mock(User.class);
    User fromUser = mock(User.class);
    FriendRequest request = mock(FriendRequest.class);

    Friendship friendship = mock(Friendship.class);
    SavedDrawing drawing = mock(SavedDrawing.class);

    @Test
    public void userTest(){
        when(toUser.getDisplayname()).thenReturn("Display");
        when(toUser.getPassword()).thenReturn("Password");
        when(toUser.addFriendRequest(request)).thenReturn(true);
        when(request.getUserTo()).thenReturn(1L);
        when(request.getUserFrom()).thenReturn(2L);
        when(request.getStatus()).thenReturn("Good");
        when(request.getId()).thenReturn(1L);
    }
    @Test
    public void friendshipTest()
    {
        when(friendship.getId()).thenReturn(1L);
        when(friendship.getUser1()).thenReturn(new User());
        when(friendship.getUser2()).thenReturn(new User());
    }

    @Test
    public void savedDrawingTest()
    {
        when(drawing.getIdNum()).thenReturn(1L);
        when(drawing.getDrawing()).thenReturn("base64code");
        when(drawing.getAssociatedWord()).thenReturn("WORD");
        when(drawing.getUserCreator()).thenReturn(1L);
    }




}
