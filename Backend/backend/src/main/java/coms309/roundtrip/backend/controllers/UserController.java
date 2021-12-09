package coms309.roundtrip.backend.controllers;

import coms309.roundtrip.backend.model.Friendship;
import coms309.roundtrip.backend.model.User;
import coms309.roundtrip.backend.repository.FriendRequestRepository;
import coms309.roundtrip.backend.repository.FriendshipRepository;
import coms309.roundtrip.backend.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import coms309.roundtrip.backend.model.FriendRequest;
//import coms309.roundtrip.backend.repository.FriendRequestRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    FriendRequestRepository requestRepo;

    @Autowired
    FriendshipRepository friendsRepo;


    /**
     * Get all users by path
     * @return Returns a list of the Entire Users in the database
     */
    @ApiOperation(value = "Gets list of users", response = Iterable.class, tags = "getUsers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @GetMapping("user/all")
    List<User> GetAllUsers(){
        return userRepo.findAll();
    }
    @ApiOperation(value = "Returns a users friends", response = Iterable.class, tags = "getFriends")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @GetMapping("user/getFriends/{id}")
    List<User> getFriendsById(@PathVariable long id)
    {

        List<User> list = new ArrayList<User>();
        List<Friendship> buffer = friendsRepo.findAll();

        for(Friendship current : buffer)
        {
            if(current.getUser1().idNum == id)
            {
                list.add(current.getUser2());
            }
            else if(current.getUser2().idNum == id)
            {
                list.add(current.getUser1());
            }
        }
        return list;
    }

    @ApiOperation(value = "Returns all friend requests FROM a given user ID", response = Iterable.class, tags = "getRequestByID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @GetMapping("user/getRequests/{id}")
    List<FriendRequest> getRequestsById(@PathVariable long id)
    {

        List<FriendRequest> list = new ArrayList<FriendRequest>();
        List<FriendRequest> buffer = requestRepo.findAll();

        for(FriendRequest current : buffer)
        {
            if(current.getUserFrom() == id)
            {
                list.add(current);
            }
            else if(current.getUserTo() == id)
            {
                list.add(current);
            }
        }
        return list;
    }

    @ApiOperation(value = "Returns a friendRequest object with the given ID", response = Iterable.class, tags = "getFriendRequest")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @GetMapping("friendRequest/getByID/{id}")
    FriendRequest getRequest(@PathVariable long id)
    {
        return requestRepo.getById(id);
    }

    @ApiOperation(value = "Gets a user by the given ID", response = Iterable.class, tags = "getUserByID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @GetMapping("user/getByID/{id}")
    User getUser(@PathVariable long id) {

        return userRepo.getById(id);}

    /**
     *
     * @param loginname
     * @param password
     * @param displayname
     * @return
     */

    @ApiOperation(value = "Creates user", response = Iterable.class, tags = "createUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @PostMapping("user/post/{loginname}/{password}/{displayname}")
    Long PostUserByPath(@PathVariable String loginname, @PathVariable String password, @PathVariable String displayname){
        User newUser = new User();
        newUser.setLoginname(loginname);
        newUser.setDisplayname(displayname);
        newUser.setPassword(password);
        userRepo.save(newUser);
        return newUser.idNum;
    }

    /**
     *
     * @param newUser
     * @return
     */
    @ApiOperation(value = "Creates a user by object", response = Iterable.class, tags = "createUserByObject")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @PostMapping("user/post")
    User PostUserByPath(@RequestBody User newUser){
        userRepo.save(newUser);
        return newUser;
    }



    /**
     * This will be a method that adds a friend to a user's friend's list
     * @param userFrom this will be the user that initiated the friend request
     * @param userTo this will be the user being friended
     * @return returns the new friend's username.
     */
    @ApiOperation(value = "Creates a friend request", response = Iterable.class, tags = "createRequest")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @PostMapping("friendRequest/post/{userTo}/{userFrom}")
    boolean PostFriendRequest(@PathVariable long userTo, @PathVariable long userFrom) {

        User user1 = userRepo.getById(userTo);
        User user2 = userRepo.getById(userFrom);
        boolean requestSuccess = false;

        FriendRequest request = new FriendRequest(userFrom, userTo);

        requestRepo.save(request);

        return user2.addFriendRequest(request) && user1.addFriendRequest(request);
    }
    @ApiOperation(value = "Denies a friendship", response = Iterable.class, tags = "denyFriendship")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @PostMapping("friendRequest/deny/{requestId}")
    void DenyFriend(@PathVariable("requestId") long requestId)
    {
        FriendRequest request = requestRepo.getById(requestId);
        request.setIdNum(requestId);
        request.deny();
        requestRepo.save(request);
    }

    @ApiOperation(value = "Accepts a friendship", response = Iterable.class, tags = "acceptFriendship")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @PostMapping("friendRequest/accept/{requestId}")
    String AcceptFriend(@PathVariable("requestId") int requestId)
    {
        System.out.println("Echo: " + requestId);
        FriendRequest request = requestRepo.getById((long) requestId);
        User userTo = userRepo.getById(request.getUserTo());
        User userFrom = userRepo.getById(request.getUserFrom());
        System.out.println("Echo " + userFrom.idNum);
        System.out.println("Echo " + userTo.idNum);
        request.accept();
        request.setIdNum((long) requestId);
        requestRepo.save(request);
        userTo.setIdNum(request.getUserTo());
        userFrom.setIdNum(request.getUserFrom());
        Friendship friendship = new Friendship(userTo, userFrom);
        friendsRepo.save(friendship);

        return userTo.getLoginname();
    }

    @ApiOperation(value = "Deletes a user", response = Iterable.class, tags = "deleteUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @DeleteMapping("user/delete/{id}")
    void deleteById(@PathVariable("id") long id)
    {
        userRepo.deleteById(id);
    }


}
