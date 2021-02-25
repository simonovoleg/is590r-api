//package russianhackers.api.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.stereotype.Repository;
//
//import russianhackers.api.model.User;
//@Repository("fakeDao")
//public class FakeUserDataAccessService implements UserDao {
//
//	private static List<User> DB = new ArrayList<User>();
//
//	@Override
//	public int insertUser(UUID user_id, User user) {
//		DB.add(new User(user_id, user.getName(), user.getEmail()));
//		return 1;
//	}
//
//	@Override public List<User> selectAllPeople() {
//		return DB;
//	}
//
//	@Override public Optional<User> selectUserById(UUID user_id) {
//		return DB.stream().filter(person -> person.getId().equals(user_id)).findFirst();
//	}
//
//	@Override public int deleteUserById(UUID user_id) {
//		Optional<User> userMaybe = selectUserById(user_id);
//		if (userMaybe.isEmpty()) {
//			return 0;
//		}
//
//		DB.remove(userMaybe.get());
//		return 1;
//	}
//
//	@Override public int updateUserById(UUID id, User update) {
//		return selectUserById(id)
//					.map(u -> {
//						int indexOfUserToUpdate = DB.indexOf(u);
//						if (indexOfUserToUpdate >= 0) {
//							DB.set(indexOfUserToUpdate, new User(id, update.getName(), update.getEmail()));
//							return 1;
//						}
//						return 0;
//					})
//					.orElse(0);
//	}
//}
