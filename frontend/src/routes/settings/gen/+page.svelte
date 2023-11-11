<div class="centered-container">
    <h1 class="page-title"><a class="same-unvisited" href="/settings">Settings</a> / General</h1>
</div>
<br/>

<div class="hv-centered-container settings-form">
    <form method="post" action="/api/user/update_settings/gen">
        <label for="name">Enter your name:</label>
        <input type="text" id="name" name="name" placeholder="John Doe" required>
        <br/>
        <label for="phone">Enter your phone number (for notifications, optional):</label>
        <input type="tel" id="phone" placeholder="123-456-678" name="phone">
        <br/>
        <label for="email">Enter your email (for notifications, optional):</label>
        <input type="email" id="email" placeholder="jdoe@example.com" name="email">
        <br/>
        <button type="submit">Submit</button>
    </form>
</div>

<style>
    .centered-container {
        display: flex;
        justify-content: center;
    }

    .hv-centered-container {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    .page-title {
        font-size: 40pt;
    }

    .settings-form {
        border: inset 2px;
        padding: 20px;
        font-size: 18pt;
    }

    a.same-unvisited {
        color:blue;
        text-decoration: none;
    }

    a.same-unvisited:visited {
        color: blue;
    }
</style>
