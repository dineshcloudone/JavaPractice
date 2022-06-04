package factorymethod;

class SMSNotification2 implements Notification {

	@Override
	public void notifyUser()
	{
		// TODO Auto-generated method stub
		System.out.println("Sending an SMS notification");
	}
}


class EmailNotification2 implements Notification {

	@Override
	public void notifyUser()
	{
		// TODO Auto-generated method stub
		System.out.println("Sending an e-mail notification");
	}
}

class PushNotification2 implements Notification {

	@Override
	public void notifyUser()
	{
		// TODO Auto-generated method stub
		System.out.println("Sending a push notification");
	}
}



class NotificationFactory_2 {
	public Notification createNotification(String channel)
	{
		if (channel == null || channel.isEmpty())
			return null;
		if ("SMS".equals(channel)) {
			return new SMSNotification();
		}
		else if ("EMAIL".equals(channel)) {
			return new EmailNotification();
		}
		else if ("PUSH".equals(channel)) {
			return new PushNotification();
		}
		return null;
	}
}


public class NotificationService2 {
	public static void main(String[] args)
	{
		NotificationFactory_2 notificationFactory = new NotificationFactory_2();
		Notification notification = notificationFactory.createNotification("EMAIL");
		notification.notifyUser();
	}
}

