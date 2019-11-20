const LOG_IN = 'LOG_IN'
const SIGN_UP = 'SIGN_UP'

// -----------------------------------

export const login = (email, password) => {
    return dispatch => {
      axios.put("https://localhost:44302/api/editTask?emailAddress=" + email) // SREDITI URL
      .then(response => {
        // srediti state!
      });
    };
};