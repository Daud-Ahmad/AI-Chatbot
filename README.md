💬 ChatGPT Android App
A powerful and user-friendly native Android application built with Java, integrating OpenAI's ChatGPT API to provide intelligent and real-time answers to user queries. This app is designed to demonstrate professional-level mobile development with features such as in-app subscriptions, local conversation history using Room, and monetization via AdMob.

✨ Features
🤖 ChatGPT API Integration
Get AI-powered responses to any question using the OpenAI ChatGPT API.

💾 Conversation History with Room DB
All questions and answers are saved locally, allowing users to revisit their chats anytime.

💳 In-App Subscription Support
Unlock premium features via secure and scalable in-app subscription flow using Google Play Billing.

📢 AdMob Integration
Monetize your app with banner and interstitial ads using Google AdMob.

🧩 Clean Architecture
Structured and maintainable codebase using MVVM, Retrofit, and Room.

🛠️ Tech Stack
Language: Java

Database: Room

Networking: Retrofit + Gson

Billing: Google Play In-App Subscriptions

Ads: Google AdMob

AI: OpenAI ChatGPT API

🚀 Getting Started
1. Clone the repository
2. Open the project in Android Studio
Make sure your environment is set up with the latest Android SDK and Google services.

3. Update API Tokens
To get the app running, update your API keys in the utils/Constant.java file:

java
Copy
Edit
public class Utils {
    public static final String OPENAI_API_KEY = "your_openai_api_key_here";
    public static final String LICENSE_KEY = "your_subscription_license_key_here";
}
You can get your OpenAI API key from https://platform.openai.com/account/api-keys

Use your Google Play Console license key for subscription validation.

4. Add AdMob App ID and Unit IDs
Replace the demo AdMob IDs with your real ones in AndroidManifest.xml and where required in your code.

5. Run the app
Connect a device or use an emulator:

🤝 Contributing
Contributions are welcome! If you'd like to fix a bug or propose a feature, feel free to open an issue or submit a pull request.

⭐ Support
If you found this project helpful or inspiring:

⭐ Star this repo

🍴 Fork it

🧑‍💻 Share it

Your support helps grow the project and reach more developers!
