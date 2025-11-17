async function attemptRedirect(fetchPoint: String | URL, redirectLocation: String | URL) {
    try {
        const response = await fetch(fetchPoint, {
            method: 'GET',
            credentials: 'include'});
        const isReady = await response.json();

        if (isReady === true) {
            window.location.href = redirectLocation;
        } else {
            // Check again in 2 seconds
            setTimeout(attemptRedirect(fetchPoint, redirectLocation), 2000);
        }
    } catch (e) {
        console.error("Error checking status", e);
        setTimeout(attemptRedirect(fetchPoint, redirectLocation), 2000);
    }
}