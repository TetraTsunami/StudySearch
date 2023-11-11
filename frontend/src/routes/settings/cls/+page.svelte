<div class="centered-container">
    <h1 class="page-title"><a class="same-unvisited" href="/settings">Settings</a> / Classes</h1>
</div>
<br/>

<div class="hv-centered-container settings-form">
    <p>To tell us what class you're in, visit <a target="_blank" href="https://my.wisc.edu/portal/p/class-schedule.ctf2/max/render.uP">this website</a>, and click the print button. Choose "Save to PDF" (or similar) to download a PDF of your schedule. </p>
    <form method="post" action="/api/user/update_settings/classes_from_pdf">
        <label for="pdf">Upload your PDF here:</label>
        <input type="file" id="pdf" name="pdf" required>
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
