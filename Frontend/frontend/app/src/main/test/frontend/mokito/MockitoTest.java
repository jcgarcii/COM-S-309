package test.frontend.mokito;

import static org.mockito.Mockito.*;

import com.example.frontend.model.Paintview;
import com.example.frontend.model.FriendRequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockitoTest 
{
    /*We need to test the interaction between PaintView 
    -and the classes it entails
    - The drawing class requires four elements, the Paint class, Canvas Class, Draw Class, and the bitmap class
    - We mostly just need to check these interactions
    */
    PaintView populate = mock(PaintView.class);

    public void paintTest()
    {
        when()
    }




    
    //User Mocking
    FriendRequest toUser = mock(FriendRequest.class);    
    FriendRequest fromUser = mock(FriendRequest.class);
/**
    @Test
    public void userTest() {
     //   when(toUser.denyFriend(fromUser.getLoginname())).thenReturn(true);
        when(request.getId()).thenReturn(1L);
        when(request.getUserFrom()).thenReturn(78L);
        when(request.getUserTo()).thenReturn(54L);
        when(request.getStatus()).thenReturn("status");
    }
**/
}
